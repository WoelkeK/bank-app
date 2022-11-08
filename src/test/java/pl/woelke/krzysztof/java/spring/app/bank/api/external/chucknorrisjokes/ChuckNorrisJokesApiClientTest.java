package pl.woelke.krzysztof.java.spring.app.bank.api.external.chucknorrisjokes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ChuckNorrisJokesApiClientTest {

    @Test
    void getRandomJoke() throws IOException {
        // given
        ChuckNorrisJokesApiClient chuckNorrisJokesApiClient = new ChuckNorrisJokesApiClient();

        // when
        ChuckNorrisJoke randomJoke = chuckNorrisJokesApiClient.getRandomJoke();

        // then
        Assertions.assertNotNull(randomJoke, "randomJoke is null");
    }
}