package srb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiConstants;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import srb.telegram.SrBTelegram;
import srb.telegram.bot.SrBTelegramBot;
import srb.telegram.bot.SrBTelegramBotMain;

@SpringBootApplication
public class SrBTradingMain {

	public static void main(String[] args) {
		
		ProxyUtil.configurarAutenticacaoProxy();
		
		try {
			SrBTelegramBotMain.main(null);
		} catch (TelegramApiException e) {
			System.out.println("SrB: a tentativa de inicar o TelegramBot falhou!");
			e.printStackTrace(); 
		}
		
		SpringApplication.run(SrBTradingMain.class, args);
		
//		SrBTelegram.enviaMsg("Teste do enviaMsg()");
		
//		SrBTelegram.start(1000*5);
		
	}

}