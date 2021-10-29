package com.broker.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author Viktor Makarov
 */
@Service
@RabbitListener
@Log4j2
public class ReceiverService {

    @RabbitListener(queues = "myQueue")
    public void onMessage(Message message) {
        log.info("Receiver service: " + new String(message.getBody()));
    }
}
