package tw.waterballsa.gaas.avalon.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "tw.waterballsa.gaas.avalon")
public class AvalonApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvalonApplication.class, args);
    }

}
