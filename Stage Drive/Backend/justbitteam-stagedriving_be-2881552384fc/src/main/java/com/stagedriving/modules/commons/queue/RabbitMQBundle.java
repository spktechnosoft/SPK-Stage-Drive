package com.stagedriving.modules.commons.queue;

import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Preconditions;
import com.google.inject.Injector;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.stagedriving.Service;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.api.Factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Dropwizard bundle for using RabbitMQ message queues.
 *
 * @author Bo Gotthardt
 */
@Slf4j
public class RabbitMQBundle { //implements ConfiguredBundle<AppConfiguration>, Managed {
    private final ConnectionFactory factory = new ConnectionFactory();
    private List<QueueWorker> workers = new ArrayList<>();
    @Getter(AccessLevel.PACKAGE)
    private Connection connection;
    private Channel channel;
    private MetricRegistry metrics;
    private AppConfiguration configuration;

    public static RabbitMQBundle rabbitMQBundle;

//    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        // Empty on purpose.
        rabbitMQBundle = this;
    }

//    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        this.configuration = configuration;
        RabbitMQConfiguration rabbitMQ = configuration.getRabbitMq();
        factory.setHost(rabbitMQ.getHost());
        factory.setVirtualHost(rabbitMQ.getVirtualHost());
        factory.setUsername(rabbitMQ.getUsername());
        factory.setPassword(rabbitMQ.getPassword());
        factory.setPort(rabbitMQ.getPort());
        factory.setAutomaticRecoveryEnabled(true);

        log.info("Connecting to RabbitMQ on '{}' with username '{}'.", factory.getHost(), factory.getUsername());

        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.basicQos(1);

//        environment.lifecycle().manage(this);
//        environment.healthChecks().register("rabbitmq", new RabbitMQHealthCheck(this));
//        metrics = environment.metrics();


        if (configuration.getWorkers() != null && !configuration.getWorkers().isEmpty()) {
            setupWorkers(configuration.getWorkers(), environment);
            log.info("Created {} workers.", workers.size());
        }
    }

    private void setupWorkers(List<WorkerConfiguration> configurations, Environment environment) {
        Injector injector = Service.getGuice().getInjector();

        configurations.forEach(config -> {
            Class<? extends QueueWorker> workerClass = config.getWorker();
            String queueName = config.getQueue();
            String exchangeType = config.getExchangeType();
            String exchange = config.getExchange();
            String routingKey = config.getKey();
            log.info("Configuring worker {} on queue {}, exchangeType {}, exchange {}, routingKey {}", workerClass.getSimpleName(), queueName, exchangeType, exchange, routingKey);
            ExecutorService executorService = environment.lifecycle()
                    .executorService(workerClass.getSimpleName() + "-%d")
                    .maxThreads(config.getThreads())
                    .build();

            for (int i = 0; i < config.getThreads(); i++) {
                QueueWorker<WorkerMessage> worker = injector.getInstance(workerClass);
                worker.setup(getQueueFactory(exchange, queueName, exchangeType, routingKey, WorkerMessage.class).provide());
                executorService.submit(worker);
                workers.add(worker);
            }
            log.info("Created {} thread{} for worker {}.", config.getThreads(), config.getThreads() > 1 ? "s" : "", workerClass.getSimpleName());
        });


//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                WorkerMessage msg = new WorkerMessage();
//                msg.setId("111");
//                RabbitMQBundle.rabbitMQBundle.getQueue("commons", WorkerMessage.class).publish(msg);
//            }
//        }, 8000);

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                WorkerMessage msg = new WorkerMessage();
//                msg.setId("222");
//                RabbitMQBundle.rabbitMQBundle.getQueueFactory("commons-direct", null, WorkerMessage.class).provide().publish(msg);
//            }
//        }, 10000);
//
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                WorkerMessage msg = new WorkerMessage();
//                msg.setId("222");
//                RabbitMQBundle.rabbitMQBundle.getQueueFactory("commons-topic", null, null, "topic.red", WorkerMessage.class).provide().publishDelayed(msg, 5000);
//            }
//        }, 10000);
    }

//    @Override
    public void start() throws Exception {
        // Connection and channel init code should really be in here, but it has to be earlier in the startup to be ready when Guice/HK2 initializes.


    }

//    @Override
    public void stop() throws Exception {
        log.info("Canceling {} workers.", workers.size());
        workers.forEach(QueueWorker::cancel);

        if (channel != null && channel.isOpen()) {
            channel.close();
        }
        if (connection != null && connection.isOpen()) {
            connection.close();
        }
    }

    /**
     * Get the message queue with the specified name.
     *
     * @param queueName The queue name.
     * @param <T>       The type of messages in the queue.
     * @return The queue.
     */
    private <T> MessageQueue<T> getQueue(String exchange, String queueName, String exchangeType, String routingKey, Class<T> type) {
        Preconditions.checkNotNull(channel, "Channel not initialized.");
        Preconditions.checkState(channel.isOpen(), "Channel already closed.");

        return new RabbitMQMessageQueue<>(channel, exchange, queueName, exchangeType, routingKey, type, metrics);
    }

    public <T> Factory<MessageQueue<T>> getQueueFactory(String exchange, String queueName, Class<T> type) {
        return getQueueFactory(exchange, queueName, null, null, type);
    }

    public <T> Factory<MessageQueue<T>> getQueueFactory(String exchange, String queueName, String exchangeType, String routingKey, Class<T> type) {
        return new Factory<MessageQueue<T>>() {
            @Override
            public MessageQueue<T> provide() {
                return getQueue(exchange, queueName, exchangeType, routingKey, type);
            }

            @Override
            public void dispose(MessageQueue<T> instance) {
            }
        };
    }

    /**
     * Delete all messages in the specified queue.
     * <b>ONLY for testing!</b>
     *
     * @param queue The queue.
     */
    void purgeQueue(MessageQueue<?> queue) {
        try {
            channel.queuePurge(queue.getName());
        } catch (IOException e) {
            throw new MessageQueueException("Unable to purge queue.", e);
        }
    }
}
