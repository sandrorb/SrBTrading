package srb.teste;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import srb.model.TradePerformanceModel;

public class SheetsQuickstart2 {
	
  private static final String APPLICATION_NAME = "SrBTrading App";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
  private static final String CREDENTIALS_FILE_PATH = "../google-credentials.json"; //para o arquivo no raiz da app, nome do arquivo sem caminho
  
  static {
	  System.out.println("SrB: diretório  app: " + System.getProperty("user.dir"));
	  System.out.println("SrB: diretório home: " + System.getProperty("user.home"));
	  try {
		System.out.println("SrB: nome do cumputador: " + InetAddress.getLocalHost().getHostName());
	  } catch (UnknownHostException e) {
		e.printStackTrace();
	  }
	  System.out.println("SrB: CREDENTIALS_FILE_PATH: " + CREDENTIALS_FILE_PATH);
	  System.out.println("SrB: lembre-se sempre de configurar as variáveis de ambiente MY_PROXY_ADDRESS, MY_PROXY_PORT, COMPUTER_NAME e POSTGRES_PASSWORD.");
	  System.out.println("SrB: lembre-se que o arquivo de crendencial não possui caminho no site e ../ no laptop com  o arquivo fora do diretório do app.");
  }

    
  private static GoogleCredential getGoogleCredentialsNovo() throws IOException, GeneralSecurityException {   
	  GoogleCredential credential = GoogleCredential.fromStream(
			                        new FileInputStream(CREDENTIALS_FILE_PATH))
			                        .createScoped(SCOPES); 
	  return credential;
  }
  

  public static TradePerformanceModel getTradePerformanceModel() throws IOException, GeneralSecurityException {
	  
	setMyProxy();
	 
    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    final String spreadsheetId = "1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE";
    final String range = "Dados!B5:F5";
    Sheets service =
    		new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getGoogleCredentialsNovo())
            .setApplicationName(APPLICATION_NAME)
            .build();
    ValueRange response = service.spreadsheets().values()
        .get(spreadsheetId, range)
        .execute();
    List<List<Object>> values = response.getValues();

    TradePerformanceModel tpm = new TradePerformanceModel();
    
	for (List column : values) {
		tpm.setNumOfTrade(Integer.parseInt(column.get(0).toString()));
		tpm.setNumOpPorMes(Double.parseDouble(column.get(1).toString()));
		tpm.setProbabilidadeAcertar(Double.parseDouble(column.get(2).toString()));
		tpm.setPayoff(Double.parseDouble(column.get(3).toString()));
		tpm.setRisco(Double.parseDouble(column.get(4).toString()));
	}
    
    return tpm;    
  }  

  
	public static void setMyProxy() {
		  
		String computername;
		try {
			computername = InetAddress.getLocalHost().getHostName();
			
			if (computername.equals(System.getenv("COMPUTER_NAME"))) {
				
				String myProxyAddress = System.getenv("MY_PROXY_ADDRESS");
				String myProxyPort = System.getenv("MY_PROXY_PORT");
				System.setProperty("https.proxyHost", myProxyAddress);
				System.setProperty("https.proxyPort", myProxyPort);
			
				System.out.println("SrB: proxy configurado com sucesso!");
				
			}else {
				System.out.println("SrB: computador " + computername + " não é do trabalho ou variável de ambiente COMPUTER_NAME não configurada. Proxy não configurado.");
			}
		} catch (UnknownHostException e) {
			System.out.println("SrB: erro ao tentar obter o nome do computador.");
			e.printStackTrace();
		}
	}
  
  
  
//public static void main(String... args) throws IOException, GeneralSecurityException {
//  
//setMyProxy();
// 
//// Build a new authorized API client service.
//final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//final String spreadsheetId = "1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE";
//final String range = "Dados!B5:E5";
//Sheets service =
//		new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getGoogleCredentialsNovo())
//        .setApplicationName(APPLICATION_NAME)
//        .build();
//ValueRange response = service.spreadsheets().values()
//    .get(spreadsheetId, range)
//    .execute();
//List<List<Object>> values = response.getValues();
//System.out.println(values.toString());
//
//StringBuilder sb = new StringBuilder();
//sb.append("{");
//for (List column : values) {
//		sb.append(" \"numOpMes\": " + column.get(0).toString() + ",");
//		sb.append(" \"probabilidade\": " + column.get(1).toString() + ",");
//		sb.append(" \"payoff\": " + column.get(2).toString() + ",");
//		sb.append(" \"risco\": " + column.get(3).toString());
//}
//System.out.println(sb.toString());
//
//}  

  
}
