package ru.demo.bankruptsbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoBankruptsBotApplication {
	private static final Logger LOG = LoggerFactory.getLogger(DemoBankruptsBotApplication.class);

	public static void main(String[] args) {
		LOG.info("DemoBankruptsBotApplication started");
		SpringApplication.run(DemoBankruptsBotApplication.class, args);
	}

}
