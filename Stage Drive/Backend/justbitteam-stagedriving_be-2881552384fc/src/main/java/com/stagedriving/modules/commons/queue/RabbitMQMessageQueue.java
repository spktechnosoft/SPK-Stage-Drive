package com.stagedriving.modules.commons.queue;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justbit.commons.TokenUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.GetResponse;
import io.dropwizard.jackson.Jackson;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * RabbitMQ implementation of {@link com.stagedriving.modules.commons.queue.MessageQueue}.
 *
 * @author Bo Gotthardt
 */
@Slf4j
class RabbitMQMessageQueue<T> implements MessageQueue<T> {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private final Channel channel;
    @Getter
    private final String name;
    @Getter
    private String queueName;
    private String routingKey;
    private final Class<T> type;
    private final String exchangeType;
    private final MetricRegistry metrics;
    private Meter publish;

    RabbitMQMessageQueue(Channel channel, String name, String queueName, String exchangeType, String routingKey, Class<T> type, MetricRegistry metrics) {
        this.channel = channel;
        this.name = name;
        this.type = type;
        this.exchangeType = exchangeType;
        this.metrics = metrics;
        this.queueName = queueName;
        this.routingKey = routingKey;
        if (this.routingKey == null) {
            this.routingKey = "";
        }
        this.publish = metrics.meter(MetricRegistry.name(name, type.getSimpleName(), name, "publish"));
        try {
            if (exchangeType != null) {
                //Map<String, Object> args = new HashMap<String, Object>();
                //args.put("x-delayed-type", exchangeType);
                //channel.exchangeDeclare(this.name, "x-delayed-message", true, false, args);
                channel.exchangeDeclare(this.name, exchangeType, true, false, null);

                if (exchangeType.equals("direct") || exchangeType.equals("topic")) {

                    this.queueName = channel.queueDeclare(this.queueName, true, false, false, null).getQueue();
                    channel.queueBind(this.queueName, this.name, this.routingKey);
                } else {
                    this.queueName = this.queueName + "-" + TokenUtils.generateUid();

                    channel.queueDeclare(this.queueName, false, false, true, null).getQueue();
                    channel.queueBind(this.queueName, this.name, this.routingKey);
                }
            }

        } catch (IOException e) {
            throw new MessageQueueException("Unable to declare queue.", e);
        }
    }

    @Override
    public void publish(T message) {
        try {
            channel.basicPublish(name, routingKey, null, MAPPER.writeValueAsBytes(message));

            publish.mark();
            if (log.isTraceEnabled()) {
                log.trace("Published to '{}' with data '{}'.", name, MAPPER.writeValueAsString(message));
            }
        } catch (IOException e) {
            throw new MessageQueueException("Unable to publish to queue.", e);
        }
    }

    @Override
    public void publishDelayed(T message, long millis) {
        try {
            Map headers = new HashMap<String, Object>();
            headers.put("x-delay", millis);
            AMQP.BasicProperties.Builder props = new AMQP.BasicProperties.Builder();
            props.headers(headers);

            channel.basicPublish(name, routingKey, props.build(), MAPPER.writeValueAsBytes(message));

            publish.mark();
            if (log.isTraceEnabled()) {
                log.trace("Published to '{}' with data '{}'.", name, MAPPER.writeValueAsString(message));
            }
        } catch (IOException e) {
            throw new MessageQueueException("Unable to publish to queue.", e);
        }
    }

    @Override
    public Closeable consume(Function<T, String> processor) {
        Consumer consumer = new FunctionConsumer<>(channel, processor, type, queueName, metrics);

        try {
            String tag = channel.basicConsume(queueName, false, consumer);
            log.info("Set up consumer '{}' for queue '{}'.", tag, name);


            return () -> channel.basicCancel(tag);
        } catch (IOException e) {
            throw new MessageQueueException("Unable to set up consumer.", e);
        }
    }

    @Override
    public T consumeNext() {
        try {
            GetResponse response = channel.basicGet(name, true);
            return MAPPER.readValue(response.getBody(), type);
        } catch (IOException e) {
            throw new MessageQueueException("Unable to consume.", e);
        }
    }
}
