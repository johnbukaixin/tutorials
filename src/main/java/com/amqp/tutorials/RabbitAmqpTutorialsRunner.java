package com.amqp.tutorials;

import com.amqp.tutorials.sender.Tut5Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
public class RabbitAmqpTutorialsRunner implements CommandLineRunner {

    @Value("${tutorial.client.duration:0}")
    private int duration;


    @Autowired
    private ConfigurableApplicationContext ctx;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Ready ... running for " + duration + "ms");
        Thread.sleep(duration);
        ctx.close();
    }
}
