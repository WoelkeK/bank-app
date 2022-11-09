package pl.woelke.krzysztof.java.spring.app.bank.api.external.chucknorrisjokes;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class ChuckNorrisJokesApiClient {
    private static final Logger LOGGER = Logger.getLogger(ChuckNorrisJokesApiClient.class.getName());
    private static final String URL = "https://api.chucknorris.io/jokes/random";

    public ChuckNorrisJoke getRandomJoke() throws IOException {
        LOGGER.info("getRandomJoke()");
        Request request = new Request.Builder().url(URL).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String responseString = response.body().string();
            LOGGER.info("getRandomJoke(...)=" + responseString);
            if (response.isSuccessful()) {
                ChuckNorrisJoke chuckNorrisJoke = new Gson().fromJson(responseString, ChuckNorrisJoke.class);
                LOGGER.info("chuckNorrisJoke " + chuckNorrisJoke);
                return chuckNorrisJoke;
            }
        }
        return null;
    }
}

// TODO: 08.11.2022 Zapisać do bazy zarty wygenerowane przez danego urzytkownika 