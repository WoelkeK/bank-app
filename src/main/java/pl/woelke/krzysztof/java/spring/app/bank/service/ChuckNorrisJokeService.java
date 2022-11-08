package pl.woelke.krzysztof.java.spring.app.bank.service;

import org.springframework.stereotype.Service;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.chucknorrisjokes.ChuckNorrisJoke;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.chucknorrisjokes.ChuckNorrisJokesApiClient;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class ChuckNorrisJokeService {
    private static final Logger LOGGER = Logger.getLogger(ChuckNorrisJokeService.class.getName());

    private ChuckNorrisJokesApiClient chuckNorrisJokesApiClient;

    public ChuckNorrisJokeService(ChuckNorrisJokesApiClient chuckNorrisJokesApiClient) {
        this.chuckNorrisJokesApiClient = chuckNorrisJokesApiClient;
    }

    //read
    public ChuckNorrisJoke read() throws IOException {
        LOGGER.info("read()");
        ChuckNorrisJoke randomJoke = chuckNorrisJokesApiClient.getRandomJoke();
        LOGGER.info("read(...)" + randomJoke);
        return randomJoke;
    }
}
