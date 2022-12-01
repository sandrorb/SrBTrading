package srb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		
		System.out.println("Antes de inicial o Spring.");
		
		SpringApplication.run(Main.class, args);
	}

}