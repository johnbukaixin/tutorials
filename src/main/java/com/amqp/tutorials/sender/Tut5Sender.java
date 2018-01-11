package com.amqp.tutorials.sender;

import com.amqp.tutorials.config.Tut5Config;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
@Component
public class Tut5Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Tut5Config config;

    private int index;

    private int count;

    private final String[] keys = {"quick.orange.rabbit",
            "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello to ");
        if (++this.index == keys.length) {
            this.index = 0;
        }
        String key = keys[this.index];
        builder.append(key).append(' ');
        builder.append(Integer.toString(++this.count));
        String message = builder.toString();
        template.convertAndSend(config.topic().getName(), key, message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
