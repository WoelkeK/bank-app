package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// TEST - jak faktyczne wejście przez adres URL w przeglądarce,
// uruchamia server HTTP i przechodzi prze wszystkie warstwy: web -> service -> repository -> DB
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

//    @Test
//    public void greetingShouldReturnDefaultMessage() throws Exception {
//        assertThat(this.restTemplate.getForObject(
//                "http://localhost:" + port + "/accounts", String.class)).contains("Accounts List");
//    }
}