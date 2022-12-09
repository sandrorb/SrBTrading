package srb.teste;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class SheetsQuickstart {
	
  private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "C:\\Users\\Sandro\\eclipse-workspace\\tokens";
//  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  /**
   * Global instance of the scopes required by this quickstart.
   * If modifying these scopes, delete your previously saved tokens/ folder.
   */
  private static final List<String> SCOPES =
      Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
//  private static final String CREDENTIALS_FILE_PATH = "C:\\Users\\Sandro\\eclipse-workspace\\google-credentials-OAuth2-desktop.json";
  private static final String CREDENTIALS_FILE_PATH = "/google-credentials-OAuth2-desktop.json";
//  private static final String CREDENTIALS_FILE_PATH = "/google-credentials-OAuth2-web.json";
//  private static final String CREDENTIALS_FILE_PATH = "/google-credentials-service.json";

  /**
   * Creates an authorized Credential object.
   *
   * @param HTTP_TRANSPORT The network HTTP Transport.
   * @return An authorized Credential object.
   * @throws IOException If the credentials.json file cannot be found.
   */
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {
    // Load client secrets.
    InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        .setAccessType("offline")
        .build();
    
    
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }
  
  
  //https://stackoverflow.com/questions/64135720/how-do-you-access-a-google-sheet-with-a-service-account-from-java
  private static GoogleCredential getGoogleCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException, GeneralSecurityException {
  GoogleCredential credential = new GoogleCredential.Builder()
  	    .setTransport(HTTP_TRANSPORT)
  	    .setJsonFactory(JSON_FACTORY)
  	    .setServiceAccountId("117602709337908712641")
  	    .setServiceAccountPrivateKeyFromP12File(new File("C:\\Users\\Sandro\\eclipse-workspace\\google-credentials-service.p12"))
  	    .setServiceAccountScopes(SCOPES)
  	    .build();
  	return credential;
  }
  
  /**
   * Prints the names and majors of students in a sample spreadsheet:
   * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
   */
  public static void main(String... args) throws IOException, GeneralSecurityException {
    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    final String spreadsheetId = "1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE";
    final String range = "Sheet2!A1:D1";
    Sheets service =
//        new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getGoogleCredentials(HTTP_TRANSPORT))
            .setApplicationName(APPLICATION_NAME)
            .build();
    ValueRange response = service.spreadsheets().values()
        .get(spreadsheetId, range)
        .execute();
    List<List<Object>> values = response.getValues();
    System.out.println(values.toString());
//    if (values == null || values.isEmpty()) {
//      System.out.println("No data found.");
//    } else {
//      System.out.println("Name, Major");
//      for (List row : values) {
//        // Print columns A and E, which correspond to indices 0 and 4.
//        System.out.printf("%s, %s\n", row.get(0), row.get(4));
//      }
//    }
    
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
}
