package pl.woelke.krzysztof.java.spring.app.bank.service.converter;

import org.springframework.stereotype.Component;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.NbpApiClient;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.model.Currency;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.model.Rate;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Component
public class CurrencyConverter {
    private static final Logger LOGGER = Logger.getLogger(CurrencyConverter.class.getName());

    private NbpApiClient nbpApiClient;

    public CurrencyConverter(NbpApiClient nbpApiClient) {
        this.nbpApiClient = nbpApiClient;
    }

    public AccountModel convertCurrency(AccountModel accountModel, String currency) throws IOException {
        LOGGER.info("convertCurrency(" + accountModel + " " + currency + ")");
        Currency rate = nbpApiClient.getRates(currency);
        LOGGER.info("rate:" + rate);

        double balance = accountModel.getBalance();
        List<Rate> rates = rate.getRates();
        if (rates != null) {
            Rate singleRate = rates.get(0);
            double convertedBalance = balance / singleRate.getMid();
            LOGGER.info("convertedBalance: " + convertedBalance);
            accountModel.setCurrencyBalance(convertedBalance);
        }
        return accountModel;
    }
}
