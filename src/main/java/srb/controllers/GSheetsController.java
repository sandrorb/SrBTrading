package srb.controllers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.HttpMethodConstraintElement;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;

import srb.GoogleAuthorizeUtil;

@RestController
public class GSheetsController {

	@RequestMapping(value = "/sheet", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String sheet() {
		
		String resposta = null;
		
		try {
			resposta = GoogleAuthorizeUtil.getDataSrB("1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE", "Dados!B5:E5");
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		
		return resposta;
	}
	
	
	@RequestMapping(value = "/ok", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String ok() {
		
		URL url;
		try {
			url = new URL("https://sheets.googleapis.com/v4/spreadsheets/{1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE}/values/{B7}?key=");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			Map<String, String> parameters = new HashMap<>();
			parameters.put("param1", "val");

			con.setDoOutput(true); 
			
//			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			InputStream is = con.getInputStream();
			 
			
						
			System.out.println(is);
			
//			out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
//			out.flush();
//			out.close();
			
			
			System.out.println("ok");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return "XXXXXXXXXXXX";
	}
	
	

}