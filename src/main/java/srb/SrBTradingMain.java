package srb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import srb.telegram.SrBTelegram;

@SpringBootApplication
public class SrBTradingMain {

	public static void main(String[] args) {
		
		SpringApplication.run(SrBTradingMain.class, args);
		
		SrBTelegram.start();
		
	}

}