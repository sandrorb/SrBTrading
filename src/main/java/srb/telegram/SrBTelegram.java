package srb.telegram;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import net.bytebuddy.asm.Advice.This;
import srb.gsheets.GSheetsAccess;


public class SrBTelegram extends TimerTask {

	static {
		configurarAutenticacaoProxy();
	}
	
	public static void configurarAutenticacaoProxy() {
		/********************************************************************/
		/* Esta parte é somente para o computador do trabalho que tem proxy */
		/* e é uma configuração um pouco diferente daquela que há para      */
		/* o GoogleSheets. Naquela não é necessário senha.
		/********************************************************************/
		String computerName = null;
		try {
			computerName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.out.println("SrB: não foi possível obter o nome do computador via .getHostName(), portanto, o proxy não será configurado para a classe " + This.class);
			//e.printStackTrace();
		}
		
		String computerNameEnv = System.getenv("COMPUTER_NAME");
		if (computerNameEnv.trim().equals("") || computerNameEnv == null) {
			System.out.println("SrB: a variável de ambiente COMPUTER_NAME não está configurada!");
		}
		
		System.out.println("   Variável computerName: " + computerName);
		System.out.println("Variável computerNameEnv: " + computerNameEnv);
		
		if (computerName != null && computerName.equals(computerNameEnv)) {
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
	    	
	    	System.out.println("SrB: proxy configurado dentro da classe SrBTelegram para o computador " + computerName);
		}
    	/********************************************************************/
    	/********************************************************************/
    	
	}
	
	public static void main(String[] args) {
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
