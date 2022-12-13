package srb.telegram.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SrBTelegramBot extends TelegramLongPollingBot{
	
	@Override
	public void onUpdateReceived(Update update) {
		
		if (update.hasMessage()) {
			Message message = update.getMessage();
			if (message.hasText()) {
				String text = message.getText();
				if (text.equals("/oi")) {
					SendMessage sm = new SendMessage();
					sm.setText("Olá, bem-vindo(a)! Sou o robô do Sandro.");
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
		return "SrBTradeBot";
	}

	@Override
	public String getBotToken() {
		String botToken = System.getenv("BOT_TOKEN");
		System.out.println("Bot Token armazenada no sistema e/ou eclipse é: " + botToken);
		return botToken;
	}

}
