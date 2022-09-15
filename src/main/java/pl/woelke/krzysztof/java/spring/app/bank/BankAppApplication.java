package pl.woelke.krzysztof.java.spring.app.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

}
// TODO: 14.09.2022 Funkcjonalnośc pozwalająca klientowi na zarządzanie kontami.
//String security, WebSecurityConfigureAdapter, User details service