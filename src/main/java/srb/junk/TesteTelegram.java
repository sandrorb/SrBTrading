package srb.junk;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.generics.TelegramBot;

public class TesteTelegram {
    public static void main(String[] args) {
    	
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        //Add Telegram token (given Token is fake)
        String apiToken = System.getenv("TELEGRAM_API_TOKEN");
        System.out.println("TELEGRAM_API_TOKEN: " + apiToken);
      
        //Add chatId (given chatId is fake)
        String chatId = System.getenv("TELEGRAM_CHAT_ID");
        System.out.println("TELEGRAM_CHAT_ID: " + chatId);
        
        String text = "Hello world!";

        urlString = String.format(urlString, apiToken, chatId, text);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}