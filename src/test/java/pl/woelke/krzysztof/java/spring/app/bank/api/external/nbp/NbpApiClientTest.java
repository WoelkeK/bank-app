package pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.CurrencyNotFoundException;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.model.Currency;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.model.Rate;


@SpringBootTest
class NbpApiClientTest {

    @Autowired
    private NbpApiClient nbpApiClient;

    @Test
    void getRates() throws Exception {
// given
        String currencyIso = "USD";
// when
        Currency currency = nbpApiClient.getRates(currencyIso);
        Rate rate = currency.getRates().get(0);
        // then
        Assertions.assertAll(

                () -> Assertions.assertNotNull(currency.getCurrency(), "currency is null"),
                () -> Assertions.assertEquals(currency.getCode(), currencyIso, "Incorrect iso"),
                () -> Assertions.assertNotNull(currency.getRates(), "missing currency rates"),
                () -> Assertions.assertNotNull(currency.getTable(), "missing currency table"),
                () -> Assertions.assertNotNull(rate.getMid(), "missing midlle rate"),
                () -> Assertions.assertNotNull(rate.getNo(), "missing number rate"),
                () -> Assertions.assertNotNull(rate.getEffectiveDate(), "missing rates date"),
                () -> Assertions.assertThrows(CurrencyNotFoundException.class, () -> {
                    Currency faultyQuery = nbpApiClient.getRates("PLN");
                    faultyQuery.getRates().get(0);
                })
        );
    }
}

// TODO: 12.10.2022 Zweryfikować wystąpienie wyjątku, przy sprawdzaniu kursu PLN.