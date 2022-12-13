package srb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import srb.telegram.SrBTelegram;
import srb.telegram.bot.SrBTelegramBotMain;

@SpringBootApplication
public class SrBTradingMain {

	public static void main(String[] args) {
		
//		ProxyUtil.configurarAutenticacaoProxy();
		
		try {
			SrBTelegramBotMain.main(null);
		} catch (TelegramApiException e) {
			System.out.println("SrB: a tentativa de inicar o TelegramBot falhou!");
			e.printStackTrace(); 
		}
		
		SpringApplication.run(SrBTradingMain.class, args);
		
		SrBTelegram.start(1000*5);
		
	}

}