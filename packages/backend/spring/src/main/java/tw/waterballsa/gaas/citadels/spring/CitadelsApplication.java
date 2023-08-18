package tw.waterballsa.gaas.citadels.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "tw.waterballsa.gaas.citadels")
public class CitadelsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CitadelsApplication.class, args);
	}
}
