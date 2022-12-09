package srb.teste;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import srb.model.TradePerformanceModel;

@RestController
public class GSheetController {

	@RequestMapping(value = "/g", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String g() {

		return "GSheet ok";
	}
	
	@RequestMapping(value = "/gpost", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String gpost() {

		return "GSheet gpost";
	}	

	
	@RequestMapping(value = "/gpost2", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<TradePerformanceModel> gpost2() {
		TradePerformanceModel tpm = new TradePerformanceModel();
		tpm.setId(1L); tpm.setNumOfTrade(500); tpm.setNumOpPorMes(8); tpm.setPayoff(1.1); tpm.setProbabilidadeAcertar(50.0); tpm.setRisco(1.5);
		return new ResponseEntity<TradePerformanceModel>(tpm, HttpStatus.CREATED);
	}	
	
}
