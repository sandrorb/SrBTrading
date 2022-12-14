package srb;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.UnknownHostException;


public class ProxyUtil {

	/********************************************************************/
	/* Esta parte é somente para o computador do trabalho que tem proxy */
	/* e é uma configuração um pouco diferente daquela que há para      */
	/* o GoogleSheets. Naquela não é necessário senha.
	/********************************************************************/	
	public static void configurarAutenticacaoProxy() {
			
		String computerName = null;
		try {
			computerName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.out.println("SrB: não foi possível obter o nome do computador via .getHostName(), portanto, o proxy não será configurado.");
			e.printStackTrace();
		}
		
		String computerNameEnv = System.getenv("COMPUTER_NAME");
		
		// Primeiro teste se é null. Se for, ja vai para o código.
		if (computerNameEnv == null || computerNameEnv.trim().equals("")) {
			System.out.println("SrB: a variável de ambiente COMPUTER_NAME não está configurada, portanto, o código para proxy não será executado.");
		}
		
		System.out.println("   Variável computerName: " + computerName);
		System.out.println("Variável computerNameEnv: " + computerNameEnv);
		
		if (computerName != null && computerName.equals(computerNameEnv)) {
	    	final String authUser = System.getenv("MY_PROXY_USER");
	    	final String authPassword = System.getenv("MY_PROXY_PASSWORD");
    	    System.out.println("SrB: autenticando proxy com " + authUser + " e " + authPassword.toString()); 
	    	Authenticator.setDefault(
	    	  new Authenticator() {
	    	    @Override
	    	    public PasswordAuthentication getPasswordAuthentication() {
	    	      return new PasswordAuthentication(authUser, authPassword.toCharArray());
	    	    }
	    	  }
	    	);
    	    
    	    String httpsProxyHost = System.getenv("MY_PROXY_ADDRESS");
    	    String httpsProxyPort = System.getenv("MY_PROXY_PORT");
			System.setProperty("https.proxyHost", httpsProxyHost);
			System.setProperty("https.proxyPort", httpsProxyPort);
	    	System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
	    	
	    	System.out.println("");
	    	System.out.println("Usando os seguintes dados (variáveis de ambiente) na classe ProxyUtil:");
	    	System.out.println("    COMPUTER_NAME: " + computerName);
	    	System.out.println(" MY_PROXY_ADDRESS: " + httpsProxyHost);
	    	System.out.println("    MY_PROXY_PORT: " + httpsProxyPort);
	    	System.out.println("    MY_PROXY_USER: " + authUser);
	    	System.out.println("MY_PROXY_PASSWORD: " + authPassword);
	    	System.out.println("");
	    	System.out.println("Usando os seguintes dados (variáveis de ambiente) outros:");
	    	System.out.println("TELEGRAM_API_TOKEN: " + System.getenv("TELEGRAM_API_TOKEN"));
	    	System.out.println("  TELEGRAM_CHAT_ID: " + System.getenv("TELEGRAM_CHAT_ID"));
	    	System.out.println("         BOT_TOKEN: " + System.getenv("BOT_TOKEN"));
	    	System.out.println("");
		}
		
//		  System.out.println("SrB: diretório   app: " + System.getProperty("user.dir"));
//		  System.out.println("SrB: diretório  home: " + System.getProperty("user.home"));
//		  System.out.println("SrB: diretório  nome: " + System.getProperty("user.name"));
//		  System.out.println("SrB: diretório email: " + System.getProperty("user.email"));//acho que este não existe desta forma		
//		  System.out.println("SrB: CREDENTIALS_FILE_PATH: " + CREDENTIALS_FILE_PATH);
		  System.out.println("SrB: lembre-se sempre de configurar as variáveis de ambiente MY_PROXY_ADDRESS,");
		  System.out.println("     MY_PROXY_PORT, COMPUTER_NAME, CREDENTIALS_FILE_PATH");
		  System.out.println("     TELEGRAM_API_TOKEN, TELEGRAM_CHAT_ID, e BOT_TOKEN");
		  System.out.println("SrB: lembre-se que o arquivo de crendencial não possui caminho no site e ../ no laptop com");
		  System.out.println("     o arquivo fora do diretório do app.");
    	
	}	

}
