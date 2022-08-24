package pl.woelke.krzysztof.java.spring.app.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

}
// TODO: 15.08.2022 stworzyć nową klasę accountMapper, która posiada metody zamieniające Account model na account entity oraz
//Account Entity na Account model