package srb;

import java.io.File;
import java.io.FileReader;

import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleAuthorizeUtil {
	
	private static Credential authorize() throws Exception {

		//Esta parte foi necessária para executar a aplicação localmente sem ser por webservice.
		//Os dados foram ocultados por meio de varíaveis de ambiente configuradas via Eclipse.
//	    String credentialLocation = System.getenv("CREDENTIAL_LOCATION");
//	    String credentialPath = credentialLocation + "/credenciais_2022-12-07-0749.json";
	    
		//Ao contrário do caso acima, aqui o caminho para o arquivo com as credenciais não foi ocultado.
	    String credentialLocation = ".";  //System.getProperty("user.dir");
	    //O arquivo com as credenciais do GoogleSheets estão fora fo projeto e não é versionado pelo git, por segurança.
	    String credentialPath = credentialLocation + "/google-credentials.json";
	    
	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new FileReader(credentialPath));

	    List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
	    
	    
	    //A ser usado somente onde houver um proxy
	    String myProxyAddress = System.getenv("MY_PROXY_ADDRESS");
	    String myProxyPort = System.getenv("MY_PROXY_PORT");
	    System.setProperty("https.proxyHost", myProxyAddress);
	    System.setProperty("https.proxyPort", myProxyPort);

	    GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow
	            .Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, scopes)
	            .setDataStoreFactory(new FileDataStoreFactory(new File(credentialLocation)))
	            .setAccessType("offline")
	            .build();
	    
	    AuthorizationCodeInstalledApp acia = new AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, new LocalServerReceiver());
	    Credential credencial = acia.authorize("user");

	    return credencial;
	}
		
	
	
	public static String[][] getData(String spreadSheetId, String sheetName, String rangeDataToRead) throws Exception {
	    Sheets sheet = new Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), authorize());
	    
	    List<List<Object>> data = sheet.spreadsheets().values()
	            .get(spreadSheetId, sheetName + "!" + rangeDataToRead)
	            .execute().getValues();
	      
	    return convertToArray(data);
	}
	
	
	private static String[][] convertToArray(List<List<Object>> data) {
	    String[][] array = new String[data.size()][];

	    int i = 0;
	    for (List<Object> row : data) {
	        array[i++] = row.toArray(new String[row.size()]);
	    }
	    return array;
	}
	

	
	public static void updateData(String spreadSheetId, String sheetName, String cellLocation, String newValue) throws Exception {
	    
		if (cellLocation.contains(":")) {
		        throw new Exception(String.format("Restricting update to single cell only. You are trying to update cells %s", cellLocation));
		}

		    Sheets sheets = new Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), authorize());

		    ValueRange updateValue = new ValueRange()
		            .setValues(Arrays.asList(Arrays.asList(newValue)));

		    sheets.spreadsheets().values()
		            .update(spreadSheetId, sheetName + "!" + cellLocation, updateValue)
		            .setValueInputOption("RAW")
		            .execute();
	}
	
	
	public static String getDataSrB(String spreadSheetId, String cellLocation) throws Exception {
		Sheets service = new Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), authorize());
		ValueRange response = service.spreadsheets().values()
				        .get(spreadSheetId, cellLocation)
				        .execute();
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
	
}