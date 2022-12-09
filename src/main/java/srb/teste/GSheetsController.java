package srb.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GSheetsController {

//	@RequestMapping(value = "/sheet", method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String sheet() {
//		
//		String resposta = null;
//		
//		try {
//			resposta = GoogleAuthorizeUtil.getDataSrB("1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE", "Dados!B5:E5");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}		
//		
//		return resposta;
//	}
	
	
	@RequestMapping(value = "/ok", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String ok() {
		StringBuffer sb = new StringBuffer();
		URL url;
		try {
			url = new URL("https://sheets.googleapis.com/v4/spreadsheets/1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE/values/B7?key=AIzaSyB75wZL6HkU_iLNP3a5r0HsIWGW6xL8ZuM");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
//			con.setDoOutput(true); 
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	

}