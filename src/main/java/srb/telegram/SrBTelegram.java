package srb.telegram;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import srb.ProxyUtil;
import srb.gsheets.GSheetsAccess;


public class SrBTelegram extends TimerTask {
		
	public static void main(String[] args) {
//		ProxyUtil.configurarAutenticacaoProxy();
//		start(1000*5);
		enviaMsg("Teste!");
	}
	
	public static void start(int milliSeconds) {
    	Timer timer = new Timer();
    	SrBTelegram srbTeste = new SrBTelegram();
    	timer.schedule(srbTeste, new Date(), milliSeconds);
    }
	
	
	// Parametrizar para pegar msg diferentes de células diferentes.
	// Por o código de produção da mensagem em uma classe separada da do seriviço de envio de mensagem. 
	public String getMsg() {
		String msg = "";
		try {
			msg = GSheetsAccess.getIFR2Dado();
		} catch (IOException e) {
			msg = "SrB: houve um erro ao tentar obter a informação";
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			msg = "SrB: houve um erro ao tentar obter a informação";
			e.printStackTrace();
		}
		return msg;
	}
	
	
	//O problema aqui é que após verificar que a venda ocorreu, o sistema fica enviando a mesma mensagem de aviso.
	public void run() {
        String msg = getMsg();
        if (msg.equals("Venda Ocorreu")) {
	        try {        	
	            String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
	            String apiToken = System.getenv("TELEGRAM_API_TOKEN");          
	            String chatId = System.getenv("TELEGRAM_CHAT_ID");
	            	urlString = String.format(urlString, apiToken, chatId, "Verifique a operação em CPLE6 pois é provável que tenha dado saída!");
	            URL url = new URL(urlString);
	            URLConnection conn = url.openConnection();
	            InputStream is = new BufferedInputStream(conn.getInputStream());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
	}
	
	public static void enviaMsg(String msg) {		
        try {        	
            String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
            String apiToken = System.getenv("TELEGRAM_API_TOKEN");          
            String chatId = System.getenv("TELEGRAM_CHAT_ID");
            urlString = String.format(urlString, apiToken, chatId, msg);
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}	
		
	
}
