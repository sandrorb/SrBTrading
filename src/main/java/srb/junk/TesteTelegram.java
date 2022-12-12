package srb.junk;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TesteTelegram {

	public static void main(String[] args) {

		/********************************************************************/
		/* Esta parte é somente para o computador do trabalho que tem proxy */
		/********************************************************************/
    	final String authUser = System.getenv("MY_PROXY_USER");
    	final String authPassword = System.getenv("MY_PROXY_PASSWORD");
    	Authenticator.setDefault(
    	  new Authenticator() {
    	    @Override
    	    public PasswordAuthentication getPasswordAuthentication() {
    	      return new PasswordAuthentication(authUser, authPassword.toCharArray());
    	    }
    	  }
    	);
		System.setProperty("https.proxyHost", System.getenv("MY_PROXY_ADDRESS"));
		System.setProperty("https.proxyPort", System.getenv("MY_PROXY_PORT"));
    	System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");	
    	/********************************************************************/
    	/********************************************************************/
    	
    	String msg = "Hello, World!";
    	
//    	try {
//			msg = GSheetsAccessIFR2.getTradePerformanceModel("IFR2!D1");
//			System.out.println("SrB: dentro de TesteTelegram tentando ler o sheet: " + msg);
//		} catch (IOException e) {
//			msg = "SrB: houve um erro ao tentar obter a informação";
//			System.out.println(msg);
//			e.printStackTrace();
//		} catch (GeneralSecurityException e) {
//			msg = "SrB: houve um erro ao tentar obter a informação";
//			System.out.println(msg);
//			e.printStackTrace();
//		}
        
        int minSeconds = 1000 * 5; // milisegundos
       
    	Timer timer = new Timer();
    	SrBTeste geraDados = new SrBTeste(msg);
    	timer.schedule(geraDados, new Date(), minSeconds);
        
//        try {        	
//            URL url = new URL(urlString);
//            URLConnection conn = url.openConnection();
//            System.out.println("URLConnection: " + conn);
//
//            InputStream is = new BufferedInputStream(conn.getInputStream());
//            System.out.println("Mensagem enviada");            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
	
}

class SrBTeste extends TimerTask{

	private String urlString;
	
	SrBTeste(String msg){
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String apiToken = System.getenv("TELEGRAM_API_TOKEN");
        System.out.println("TELEGRAM_API_TOKEN: " + apiToken);
      
        String chatId = System.getenv("TELEGRAM_CHAT_ID");
        System.out.println("TELEGRAM_CHAT_ID: " + chatId);

        urlString = String.format(urlString, apiToken, chatId, msg);
        System.out.println(urlString);
        
        this.urlString = urlString;
	}

	@Override
	public void run() {
		
        try {        	
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            System.out.println("URLConnection: " + conn);

            InputStream is = new BufferedInputStream(conn.getInputStream());
            System.out.println("Mensagem enviada");            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
}