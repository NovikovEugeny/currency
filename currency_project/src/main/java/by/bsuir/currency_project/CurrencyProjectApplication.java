package by.bsuir.currency_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyProjectApplication.class, args);
	}
}
