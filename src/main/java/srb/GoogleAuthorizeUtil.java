package srb;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleAuthorizeUtil {
	
	
	private static final String APPLICATION_NAME = "SrBTrading";
//	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();//GsonFactory.getDefaultInstance();
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	
//	private static final String TOKENS_DIRECTORY_PATH = ".";
//	private static final String CREDENTIALS_FILE_PATH = "/google-credentials-desktop.json";
//	private static final String CREDENTIALS_FILE_PATH = "/google-credentials-service.json";
//	private static final String CREDENTIALS_FILE_PATH = "/srbtrading-21eacb41c260.json";
	
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);//Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

	
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {

//	    String credentialLocation = "..";
//	    String credentialPath = credentialLocation + "/google-credentials-desktop.json";
	    
		// Load client secrets.
//	    InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//	    if (in == null) {
//	      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
//	    }
    
	    
	    //GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new FileReader(credentialPath));
//	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
	    
	    //A ser usado somente onde houver um proxy
	    String myProxyAddress = System.getenv("MY_PROXY_ADDRESS");
	    String myProxyPort = System.getenv("MY_PROXY_PORT");
	    System.setProperty("https.proxyHost", myProxyAddress);
	    System.setProperty("https.proxyPort", myProxyPort);
	    
	    // Build flow and trigger user authorization request.
//	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//	        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//	        .setAccessType("offline")
//	        .build();
	    

	    //https://developers.google.com/api-client-library/java/google-api-java-client/oauth2#web_server_applications
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
	    		"XXXXXXXXXXXXXXXXXXXXXXXX", "YYYYYYYYYYYYYYYYYYYYY", SCOPES)
	    		//.setDataStoreFactory(DATA_STORE_FACTORY)
	    		.setAccessType("offline")
	    		.build();


	    //LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
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
		

	
	public static String getDataSrB(String spreadSheetId, String cellLocation) throws Exception {
		
		
	
//	    final String spreadsheetId = "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";
//	    final String range = "Class Data!A2:E";
	    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	    Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,  getCredentials(HTTP_TRANSPORT))
	            .setApplicationName(APPLICATION_NAME)
	            .build();		
	    	
//	    GoogleClientRequestInitializer KEY_INITIALIZER = CommonGoogleClientRequestInitializer.newBuilder().setKey("AIzaSyDl3D0ttyiMZyLsHdMJXPQcbPnTzfBkk04").build();	    
//		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME)
//                .setGoogleClientRequestInitializer(KEY_INITIALIZER)
//                .build();
	    
	    
		//Sheets service = new Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), authorize());
		
		ValueRange response = service.spreadsheets().values().get(spreadSheetId, cellLocation).execute();
		
		List<List<Object>> values = response.getValues();
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (List column : values) {
				sb.append(" \"numOpMes\": " + column.get(0).toString() + ",");
				sb.append(" \"probabilidade\": " + column.get(1).toString() + ",");
				sb.append(" \"payoff\": " + column.get(2).toString() + ",");
				sb.append(" \"risco\": " + column.get(3).toString());
		}
		sb.append("}");
		
		return sb.toString();
	}

	
	
	
	
	
	
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