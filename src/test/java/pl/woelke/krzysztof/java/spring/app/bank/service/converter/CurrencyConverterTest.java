package pl.woelke.krzysztof.java.spring.app.bank.service.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.NbpApiClient;
import pl.woelke.krzysztof.java.spring.app.bank.service.AccountService;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

@SpringBootTest
class CurrencyConverterTest {

    @Autowired
    private NbpApiClient nbpApiClient;
    @Autowired
    private AccountService accountService;

    @Test
    void convertCurrency() throws Exception{
        String currency = "USD";

        AccountModel accountModel = new AccountModel();
        accountModel.setBalance(100);

        // when
        AccountModel convertedCurrency = accountService.convertCurrency(accountModel, currency);

        // then
        Assertions.assertTrue(convertedCurrency.getCurrencyBalance() != 0);
    }
}