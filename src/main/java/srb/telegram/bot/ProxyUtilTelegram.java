package srb.telegram.bot;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.UnknownHostException;


public class ProxyUtilTelegram {

	
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
	    		    	
	    	System.out.println("SrB: o proxy foi configurado para o computador " + computerName);
		}
		
//		  System.out.println("SrB: diretório   app: " + System.getProperty("user.dir"));
//		  System.out.println("SrB: diretório  home: " + System.getProperty("user.home"));
//		  System.out.println("SrB: diretório  nome: " + System.getProperty("user.name"));
//		  System.out.println("SrB: diretório email: " + System.getProperty("user.email"));//acho que este não existe desta forma		
//		  System.out.println("SrB: CREDENTIALS_FILE_PATH: " + CREDENTIALS_FILE_PATH);
		  System.out.println("SrB: lembre-se sempre de configurar as variáveis de ambiente MY_PROXY_ADDRESS,");
		  System.out.println("     MY_PROXY_PORT, COMPUTER_NAME, POSTGRES_PASSWORD, TELEGRAM_API_TOKEN, TELEGRAM_CHAT_ID e CREDENTIALS_FILE_PATH");
		  System.out.println("SrB: lembre-se que o arquivo de crendencial não possui caminho no site e ../ no laptop com");
		  System.out.println("     o arquivo fora do diretório do app.");
    	
	}	

}
