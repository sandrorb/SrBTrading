package srb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiConstants;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import srb.telegram.SrBTelegram;
import srb.telegram.bot.SrBTelegramBot;
import srb.telegram.bot.SrBTelegramBotMain;

//extends SpringBootServletInitializer dever ser usado se for fazer deploy como war file.

@SpringBootApplication
public class SrBTradingMain {

	public static void main(String[] args) {
		
		SpringApplication.run(SrBTradingMain.class, args);
		
		ProxyUtil.configurarAutenticacaoProxy();
		
		SrBTelegramBotMain.main(null);
//		SrBTelegram.enviaMsg("Usando a classe SrBTelegram de envio por url");
//		SrBTelegram.start(1000*5);
		
	}

}