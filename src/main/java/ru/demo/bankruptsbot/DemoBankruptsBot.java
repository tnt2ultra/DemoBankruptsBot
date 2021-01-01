package ru.demo.bankruptsbot;

import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class DemoBankruptsBot extends TelegramLongPollingBot {

	private static final Logger LOG = LoggerFactory.getLogger(TelegramLongPollingBot.class);

	@Value("${bot.name}")
	private String botUsername;

	@Value("${bot.token}")
	private String botToken;

	@Override
	public void onUpdateReceived(Update update) {
		LOG.info("onUpdateReceived started");
		Message message = update.getMessage();
		User user = message.getFrom();
		LOG.info("user " + user);
		try {
			String version = parseFedres();
			sendMsg(message, "Сайт bankrot.fedresurs.ru " + version);
		} catch (TelegramApiException e) {
			System.out.println(e);
		}
	}

	private String parseFedres() {
		FirefoxOptions options = new FirefoxOptions();
		options.setHeadless(true);
		options.setLogLevel(FirefoxDriverLogLevel.FATAL);

		// Init webdriver
		System.setProperty("webdriver.gecko.driver",
				"C:\\Program Files (x86)\\Common Files\\Oracle\\Java\\javapath\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Try and load the website
		try {
			driver.get("https://bankrot.fedresurs.ru/help.aspx");
		} catch (WebDriverException err) {
			// Catch possible network error
			System.out.println("There was an error getting the website");
			driver.quit();
			err.printStackTrace();
		}

		String html = driver.getPageSource();
		String version = parseFedresJsoup(html);
		driver.quit();
		return version;
	}

	private String parseFedresJsoup(String html) {
		Document doc = Jsoup.parse(html);
		LOG.info(doc.title());
		Elements newsHeadlines = doc.select("#right");
		String text = newsHeadlines.html();

		int index = text.indexOf("Описание сервиса передачи реестров, торгов, отчетов АУ и опубликованных сообщений");
		String version = text.substring(index + 83, index + 108);

		index = text.lastIndexOf("Описание сервиса передачи реестров, торгов, отчетов АУ и опубликованных сообщений");
		version = version + "\nПредыдущая " + text.substring(index + 83, index + 110);
		LOG.info(version);

		return version;
	}

	public void sendMsg(Message message, String s) throws TelegramApiException {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setReplyToMessageId(message.getMessageId());
		sendMessage.setText(s);
		execute(sendMessage);
	}

	@Override
	public String getBotUsername() {
//		LOG.info("getBotUsername started " + botUsername);
		return botUsername;
	}

	@Override
	public String getBotToken() {
//		LOG.info("getBotToken started " + botToken);
		return botToken;
	}

}