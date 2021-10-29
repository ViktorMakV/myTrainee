package com.broker.service;

import com.broker.configuration.MessageConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Viktor Makarov
 */
@EnableRabbit
@Service
@RequiredArgsConstructor
public class SenderService {
    private final MessageConfiguration service;
    private final AmqpTemplate template;

    public void emit() {
        template.convertAndSend("myQueue", service.getMessage());
    }
}
