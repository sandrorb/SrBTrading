package srb.teste;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		try {
			GoogleAuthorizeUtil.teste();
			System.out.println("Executado");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		

//		File f = new File(System.clearProperty("user.dir")+"\\..\\google-credentials.json");
//		System.out.println(f.getAbsolutePath());
////		File f = new File("..\\google-credentials.json");
//		try {
//			Scanner scanner = new Scanner(f);
//			while (scanner.hasNextLine()) {
//				System.out.println(scanner.nextLine());
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		
		
		
//		String resposta = null;
//		try {
//			resposta = GoogleAuthorizeUtil.getDataSrB("1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE", "Dados!B5:E5");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}	

	}

}
