package srb.telegram.bot;


//https://core.telegram.org/bots/tutorial

//import org.telegram.telegrambots.ApiContextInitializer;//versão 4.9
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class SrBTelegramBotMain {

	public static void main(String[] args) {
		
        TelegramBotsApi botsApi;
        
        try {
        	botsApi = new TelegramBotsApi(DefaultBotSession.class);
        	System.out.println("SrB: tentando registrar TelegramBot...");
            botsApi.registerBot(new SrBTelegramBot());
            System.out.println("SrB: TelegramBot registrado.");
        } catch (TelegramApiException e) {
        	System.out.println("SrB: erro ao tentar fazer o registro do TelegramBot!");
            e.printStackTrace();
        }

	}	

}
