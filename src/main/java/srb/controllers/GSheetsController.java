package srb.controllers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
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