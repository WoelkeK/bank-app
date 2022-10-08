package pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.model.Currency;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class NbpApiClient {
    private static final Logger LOGGER = Logger.getLogger(NbpApiClient.class.getName());

    public static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/";
    private OkHttpClient client = new OkHttpClient();

    public Currency getRates(String currencyIso) throws IOException {
        LOGGER.info("getRates(" + currencyIso + ")");
        Request request = new Request.Builder().url(NBP_API_URL + currencyIso).build();

        try (Response response = client.newCall(request).execute()) {
            String responseString = response.body().string();
            Currency currency = new Gson().fromJson(responseString, Currency.class);
            LOGGER.info("getRates(...)=" + currency);
            return currency;
        }
    }
}
// TODO: 07.10.2022 Napisaś JUnit5 dla metody getRates 