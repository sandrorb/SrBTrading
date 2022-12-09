package srb.teste;

import java.io.FileInputStream;
import java.io.IOException;
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

public class SheetsQuickstart2 {
	
  private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
  private static final String CREDENTIALS_FILE_PATH = "C:\\Users\\sandro.boschetti\\eclipse-workspace\\google-credentials-service.json";

    
  private static GoogleCredential getGoogleCredentialsNovo() throws IOException, GeneralSecurityException {
//	  GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("C:\\Users\\sandro.boschetti\\eclipse-workspace\\google-credentials-service.json")).createScoped(SCOPES);
	  GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH)).createScoped(SCOPES);
	  return credential;
  }
  
  public static void main(String... args) throws IOException, GeneralSecurityException {
	  
	setMyProxy();
	 
    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    final String spreadsheetId = "1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE";
    final String range = "Dados!B5:E5";
    Sheets service =
    		new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getGoogleCredentialsNovo())
            .setApplicationName(APPLICATION_NAME)
            .build();
    ValueRange response = service.spreadsheets().values()
        .get(spreadsheetId, range)
        .execute();
    List<List<Object>> values = response.getValues();
    System.out.println(values.toString());
    
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	for (List column : values) {
			sb.append(" \"numOpMes\": " + column.get(0).toString() + ",");
			sb.append(" \"probabilidade\": " + column.get(1).toString() + ",");
			sb.append(" \"payoff\": " + column.get(2).toString() + ",");
			sb.append(" \"risco\": " + column.get(3).toString());
	}
	System.out.println(sb.toString());
    
  }
  
  
  
  public static void setMyProxy() {
	    //A ser usado somente onde houver um proxy
	    String myProxyAddress = System.getenv("MY_PROXY_ADDRESS");
	    String myProxyPort = System.getenv("MY_PROXY_PORT");
	    System.setProperty("https.proxyHost", myProxyAddress);
	    System.setProperty("https.proxyPort", myProxyPort);
  }
  
}
