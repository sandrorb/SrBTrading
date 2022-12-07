package srb.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import srb.GoogleAuthorizeUtil;

@RestController
public class GSheetsController {

	@RequestMapping(value = "/sheet", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String teste() {
		
		String resposta = null;
		
		try {
			resposta = GoogleAuthorizeUtil.getDataSrB("1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE", "Dados!B5:E5");
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		
		return resposta;
	}

}