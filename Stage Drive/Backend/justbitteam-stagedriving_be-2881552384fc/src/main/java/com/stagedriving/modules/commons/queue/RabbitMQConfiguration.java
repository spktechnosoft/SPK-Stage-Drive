package com.stagedriving.modules.commons.queue;

import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;

/**
 *
 *
 * @author Bo Gotthardt
 */
@Getter
public class RabbitMQConfiguration {
    private String host = ConnectionFactory.DEFAULT_HOST;
    private String virtualHost = ConnectionFactory.DEFAULT_VHOST;
    private int port = ConnectionFactory.DEFAULT_AMQP_PORT;
    private String username = ConnectionFactory.DEFAULT_USER;
    private String password = ConnectionFactory.DEFAULT_PASS;
}
