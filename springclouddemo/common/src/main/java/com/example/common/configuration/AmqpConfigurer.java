package com.example.common.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmqpConfigurer {
    @Bean
    public Queue queue() {
        return new Queue("ligy-queue", Boolean.TRUE);
    }
}
