package srb.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradePerformanceController {
	
//	final TradePerformanceRepository tradePerformanceRepository;
//	
//	public TradePerformanceController(TradePerformanceRepository tradePerformanceRepository) {
//		this.tradePerformanceRepository = tradePerformanceRepository;
//	}


//	@RequestMapping(value = "/teste/{texto}", method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String teste(@PathVariable String texto) {
//		File f = new File("teste.txt");
//		String str = null;
//		try {
//			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
//			bw.write(texto);
//			bw.flush();
//			bw.close();
//			str = "Texto gravado com sucesso!";
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return str;
//	}	

	
//	@RequestMapping(value = "/teste", method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String teste() {
//		File f = new File("teste.txt");
//		String str = "";
//		String st = "";
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(f));
//			while ((st = br.readLine()) != null) {
//				str = str + st;
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return str;
//	}
	

	
//	@PostMapping(value = "/todos")
//	@ResponseBody
//	public ResponseEntity<List<TradePerformanceModel>> buscarTodos(){
//		List<TradePerformanceModel> tpm = tradePerformanceRepository.findAll();
//		return new ResponseEntity<List<TradePerformanceModel>>(tpm, HttpStatus.CREATED);
//	}

	
/* Método criado apenas para testar no browser, já que não consegui mostrar no browser pelo POST */	
//	@GetMapping(value = "/todos")
//	@ResponseBody
//	public ResponseEntity<List<TradePerformanceModel>> buscarTodosGet(){
//		List<TradePerformanceModel> tpm = tradePerformanceRepository.findAll();
//		return new ResponseEntity<List<TradePerformanceModel>>(tpm, HttpStatus.CREATED);
//	}		
	
	
/* Método criado apenas para teste e lembrete */	
//	//@RequestMapping(value = "/user/{nome}", method = RequestMethod.GET)
//	@RequestMapping(value = "/teste", method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	//public String salvarUser(@PathVariable String nome) {
//	public String teste() {
//		TradePerformanceModel tradePerformanceModel = new TradePerformanceModel();
//		tradePerformanceModel.setNumOfTrade(500);
//		tradePerformanceModel.setNumOpPorMes(8.6);
//		tradePerformanceModel.setPayoff(1.1);
//		tradePerformanceModel.setProbabilidadeAcertar(61.0);
//		tradePerformanceModel.setRisco(1.5);
//		tradePerformanceRepository.save(tradePerformanceModel);
//		return "Dados Inseridos!";
//	}
	
	
/* Método testado e funcionando, mas, em princípio, deverá ser modificado para
 * não permitir mais de uma tupla no BD, inicialmente. Então acho que não será 
 * POST e sim PUT para fazer o update e não uma adição. */	
//	@PostMapping(value = "/salvar")
//	@ResponseBody
//	public ResponseEntity<Object> salvar(@RequestBody TradePerformanceModel tpm){
//		TradePerformanceModel tradePM = tradePerformanceRepository.save(tpm);
//		return new ResponseEntity<Object>(tradePM, HttpStatus.CREATED);
//	}
	
	
/* Método criado apenas para teste e lembrete */	
//	@RequestMapping(value = "/srb/{nome}", method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String srb(@PathVariable String nome) {
//		String pgPasswd = System.getenv("POSTGRES_PASSWORD");
//		return "A senha é " + pgPasswd;
//	}

	
}
