package pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.model.Currency;

import java.io.IOException;

@SpringBootTest
class NbpApiClientTest {

    @Autowired
    private NbpApiClient nbpApiClient;

    @Test
    void getRates() throws IOException {
// given
        String currencyIso = "CHF";
// when
        Currency currency = nbpApiClient.getRates(currencyIso);
// then
        Assertions.assertAll(

                () -> Assertions.assertNotNull(currency, "currency is null"),
                () -> Assertions.assertEquals(currency.getCode(), currencyIso, "Incorrect iso"),
                () -> Assertions.assertNotNull(currency.getRates(), "missing currency rates")
        );
    }
}

// TODO: 12.10.2022 Zweryfikować wystąpienie wyjątku, przy sprawdzaniu kursu PLN.