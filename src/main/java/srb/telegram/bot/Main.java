package srb.telegram.bot;


//https://core.telegram.org/bots/tutorial

//import org.telegram.telegrambots.ApiContextInitializer;//versão 4.9
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

	public static void main(String[] args) throws TelegramApiException {
		
//		ProxyUtilTelegram.configurarAutenticacaoProxy();
		
//        ApiContextInitializer.init();        
//        TelegramBotsApi botsApi = new TelegramBotsApi();//versão 4.9
		
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        
        try {
            botsApi.registerBot(new MyFirstBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

	}
	

}
