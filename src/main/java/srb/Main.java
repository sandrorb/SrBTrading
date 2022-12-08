package srb;

public class Main {

	public static void main(String[] args) {

		String resposta = null;
		
		try {
			resposta = GoogleAuthorizeUtil.getDataSrB("1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE", "Dados!B5:E5");
		} catch (Exception e1) {
			e1.printStackTrace();
		}	

	}

}
