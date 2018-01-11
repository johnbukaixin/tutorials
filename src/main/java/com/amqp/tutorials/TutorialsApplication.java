package com.amqp.tutorials;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TutorialsApplication {
	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
                System.out.println("This app uses Spring Profiles to control its behavior.\n");
                System.out.println("Options are: ");
                System.out.println("java -jar rabbit-tutorials.jar --spring.profiles.active=topics,receiver");
                System.out.println("java -jar rabbit-tutorials.jar --spring.profiles.active=topics,sender");
			}
		};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitAmqpTutorialsRunner();
	}


	public static void main(String[] args) {
		SpringApplication.run(TutorialsApplication.class, args);
	}

}
