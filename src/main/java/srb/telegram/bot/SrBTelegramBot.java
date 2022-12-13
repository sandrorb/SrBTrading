package srb.telegram.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SrBTelegramBot extends TelegramLongPollingBot{
	
//	public SrBTelegramBot() {
//		super();
//		System.setProperty("https.proxyHost", System.getenv("MY_PROXY_ADDRESS"));
//		System.setProperty("https.proxyPort", System.getenv("MY_PROXY_PORT"));
//    	System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
//	}
	
	@Override
	public void onUpdateReceived(Update update) {
		
		if (update.hasMessage()) {
			Message message = update.getMessage();
			if (message.hasText()) {
				String text = message.getText();
				if (text.equals("/oi")) {
					SendMessage sm = new SendMessage();
					sm.setText("Olá, bem-vindo(a)! Sou o robô do Sandro. Seu id é " + message.getChatId().toString() + ". Para ver os comandos disponívels, digite /help");
					sm.setParseMode(ParseMode.MARKDOWN);
					sm.setChatId(message.getChatId().toString());
					try {
						execute(sm);
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			if (message.hasText()) {
				String text = message.getText();
				if (text.equals("/help")) {
					SendMessage sm = new SendMessage();
					sm.setText("Sinto em te desapontar, mas até o momento o único comando que eu reconheço é o /oi mesmo. Em breve eu atenderei por mais comandos.");
					sm.setParseMode(ParseMode.MARKDOWN);
					sm.setChatId(message.getChatId().toString());
					try {
						execute(sm);
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				}
			}			
			
		}
		
	}

	@Override
	public String getBotUsername() {
		return "SrBTesteBot";
	}

	@Override
	public String getBotToken() {
		String botToken = System.getenv("BOT_TOKEN");
		System.out.println("Bot Token armazenada no sistema e/ou eclipse é: " + botToken);
		return botToken;
	}

}
