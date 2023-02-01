package com.example.mqlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class testListener {

    @RabbitListener(queues = "ligy-queue")
    public void get(Object object) {
        log.info(object.toString());
    }
}
