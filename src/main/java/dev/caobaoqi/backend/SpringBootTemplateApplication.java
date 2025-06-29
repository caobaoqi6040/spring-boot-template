package dev.caobaoqi.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
public class SpringBootTemplateApplication {

	public static void main(String[] args) {
		// https://docs.spring.io/spring-boot/3.4/reference/using/devtools.html#using.devtools.restart.disable
		// System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(SpringBootTemplateApplication.class, args);
	}

	@EventListener
	public void handleApplicationReadyEvent(ApplicationReadyEvent event) {
		log.info("HandleApplicationReadyEvent the application context is {}", event.getApplicationContext());
	}


}
