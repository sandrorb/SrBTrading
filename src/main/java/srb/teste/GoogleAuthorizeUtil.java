package srb.teste;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.http.entity.InputStreamEntity;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import srb.SrBTradingMain;

public class GoogleAuthorizeUtil {
	
	
	private static final String APPLICATION_NAME = "SrBTrading";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();//GsonFactory.getDefaultInstance();
//	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	
	private static final String TOKENS_DIRECTORY_PATH = "tokens"; //System.getProperty("user.dir");
	private static final String CREDENTIALS_FILE_PATH = "/google-credentials.json"; 
	
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);//Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
	
	
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
		
//	    String credentialLocation = "..";
//	    String credentialPath = credentialLocation + "google-credentials-desktop.json"; 
	     
		// Load client secrets. Este é o original GoogleClientSecrets.load(JSON_FACTORY, in);
	    InputStream in = SrBTradingMain.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//		File f = new File(System.clearProperty("user.home"));
//		System.out.println(f.getAbsoluteFile());
//	    InputStream in = new FileInputStream(f);
	    if (in == null) {
	      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
	    }
//	    
//	    InputStreamReader isr = new InputStreamReader(in);
//	    FileReader fr = new FileReader(f);
//	    Scanner s = new Scanner(isr);
//	    while (s.hasNext()) {
//	    	System.out.println(s.nextLine());
//	    }
//	    
	    
	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new FileReader(CREDENTIALS_FILE_PATH));
//	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(in));
//	    GoogleCredentials clientSecrets = GoogleCredentials.fromStream(in).createScoped(SCOPES);
	    
 
	    
	    //A ser usado somente onde houver um proxy
//	    String myProxyAddress = System.getenv("MY_PROXY_ADDRESS");
//	    String myProxyPort = System.getenv("MY_PROXY_PORT");
//	    System.setProperty("https.proxyHost", myProxyAddress);
//	    System.setProperty("https.proxyPort", myProxyPort);
	    
	    
	    // Build flow and trigger user authorization request.
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	        .setAccessType("offline")
	        .build();

	    //https://developers.google.com/api-client-library/java/google-api-java-client/oauth2#web_server_applications
//	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
//	    		"XXXXXXXX", "YYYYYYY", SCOPES)
//	    		.setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
//	    		.setAccessType("offline")
//	    		.build();


//	    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
	    LocalServerReceiver receiver = new LocalServerReceiver.Builder().build();
	    //return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	    AuthorizationCodeInstalledApp acia =  new AuthorizationCodeInstalledApp(flow, receiver);
	    
//	    GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow
//	            .Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, SCOPES)
//	            .setDataStoreFactory(new FileDataStoreFactory(new File(credentialLocation)))
//	            .setAccessType("offline")
//	            .build();
	    
	    //AuthorizationCodeInstalledApp acia = new AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, new LocalServerReceiver());
	    Credential credencial = acia.authorize("user");
	    
	    return credencial;
	}
	
	//https://github.com/googleworkspace/java-samples/issues/93
	static void teste() throws IOException, GeneralSecurityException {
		
//		File f = new File(System.clearProperty("user.home"));
//		System.out.println(f.getAbsoluteFile());
		
//		InputStream in = SrBTradingMain.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//		FileInputStream in = new FileInputStream(new File("C:\\Users\\Sandro\\eclipse-workspace\\google-credentials.json"));
		FileInputStream in = new FileInputStream(new File("C:\\Users\\Sandro\\eclipse-workspace\\google-credentials-OAuth2-desktop.json"));
		Scanner s = new Scanner(in);
		while (s.hasNextLine()) {
			System.out.println(s.nextLine());
		}
		if (in == null) {
		        throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(in)
				.createScoped(SCOPES);
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpCredentialsAdapter(googleCredentials))
		                .setApplicationName(APPLICATION_NAME)
		                .build();
		
		ValueRange response = service.spreadsheets()
				.values()
				.get("1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE", "Dados!B5:E5")
				.execute();
		
//		List<List<Object>> values = response.getValues();
		
//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		for (List column : values) {
//				sb.append(" \"numOpMes\": " + column.get(0).toString() + ",");
//				sb.append(" \"probabilidade\": " + column.get(1).toString() + ",");
//				sb.append(" \"payoff\": " + column.get(2).toString() + ",");
//				sb.append(" \"risco\": " + column.get(3).toString());
//		}		
	}

	
	
	
//	public static String getDataSrB(String spreadSheetId, String cellLocation) throws Exception {
//
////	    final String spreadsheetId = "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";
////	    final String range = "Class Data!A2:E";
//	    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//	    Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,  getCredentials(HTTP_TRANSPORT))
//	            .setApplicationName(APPLICATION_NAME)
//	            .build();
//	    
//	    
////	    GoogleClientRequestInitializer KEY_INITIALIZER = CommonGoogleClientRequestInitializer.newBuilder().setKey("AIzaSyDl3D0ttyiMZyLsHdMJXPQcbPnTzfBkk04").build();	    
////		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
////                .setApplicationName(APPLICATION_NAME)
////                .setGoogleClientRequestInitializer(KEY_INITIALIZER)
////                .build();
//	    
//	    
//		//Sheets service = new Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), authorize());
//		
//		ValueRange response = service.spreadsheets().values().get(spreadSheetId, cellLocation).execute();
//		
//		List<List<Object>> values = response.getValues();
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		for (List column : values) {
//				sb.append(" \"numOpMes\": " + column.get(0).toString() + ",");
//				sb.append(" \"probabilidade\": " + column.get(1).toString() + ",");
//				sb.append(" \"payoff\": " + column.get(2).toString() + ",");
//				sb.append(" \"risco\": " + column.get(3).toString());
//		}
//		sb.append("}");
//		
//		return sb.toString();
//	}

	
	
	
	
	
	
//	public static String[][] getData(String spreadSheetId, String sheetName, String rangeDataToRead) throws Exception {
//	    Sheets sheet = new Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), authorize());
//	    
//	    List<List<Object>> data = sheet.spreadsheets().values()
//	            .get(spreadSheetId, sheetName + "!" + rangeDataToRead)
//	            .execute().getValues();
//	      
//	    return convertToArray(data);
//	}
//	
//	
//	private static String[][] convertToArray(List<List<Object>> data) {
//	    String[][] array = new String[data.size()][];
//
//	    int i = 0;
//	    for (List<Object> row : data) {
//	        array[i++] = row.toArray(new String[row.size()]);
//	    }
//	    return array;
//	}
	

	
//	public static void updateData(String spreadSheetId, String sheetName, String cellLocation, String newValue) throws Exception {
//	    
//		if (cellLocation.contains(":")) {
//		        throw new Exception(String.format("Restricting update to single cell only. You are trying to update cells %s", cellLocation));
//		}
//
//		    Sheets sheets = new Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), authorize());
//
//		    ValueRange updateValue = new ValueRange()
//		            .setValues(Arrays.asList(Arrays.asList(newValue)));
//
//		    sheets.spreadsheets().values()
//		            .update(spreadSheetId, sheetName + "!" + cellLocation, updateValue)
//		            .setValueInputOption("RAW")
//		            .execute();
//	}
	
	
	
}