package srb.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import srb.gsheets.GSheetsAccess;
import srb.model.TradePerformanceModel;
import srb.telegram.SrBTelegram;

@RestController
public class GSheetsController {


	@RequestMapping(value = "/tpm", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<TradePerformanceModel> tpm() throws IOException, GeneralSecurityException {
		TradePerformanceModel tpm = GSheetsAccess.getTradePerformanceModel();
		return new ResponseEntity<TradePerformanceModel>(tpm, HttpStatus.CREATED);
	}	
	
}
