package ru.demo.bankruptsbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.telegram.telegrambots.ApiContextInitializer;
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.meta.generics.LongPollingBot;

@SpringBootApplication
public class DemoBankruptsBotApplication {
	private static final Logger LOG = LoggerFactory.getLogger(DemoBankruptsBotApplication.class);

	public static void main(String[] args) {
		LOG.info("DemoBankruptsBotApplication started");
		
//		ApiContextInitializer.init();
//		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//		try {
//			telegramBotsApi.registerBot((LongPollingBot) new DemoBankruptsBot());
//		} catch (TelegramApiException e) {
//			e.printStackTrace();
//		}

		SpringApplication.run(DemoBankruptsBotApplication.class, args);
	}

}
