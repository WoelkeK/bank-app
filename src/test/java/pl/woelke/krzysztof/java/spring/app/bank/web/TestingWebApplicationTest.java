package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
// TEST - jak faktyczne wejście przez adres URL w przeglądarce,
// NIE uruchamia server HTTP i przechodzi prze wszystkie warstwy: web -> service -> repository -> DB
public class TestingWebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "CRIS")
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/accounts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Accounts List")));
    }

    @Test
    @WithMockUser(username = "CRIS")
    public void shouldReadAccount() throws Exception {
        AccountModel accountModel = new AccountModel();

        this.mockMvc.perform(get("/accounts/read/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("read-account.html"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("account"))
                .andExpect(MockMvcResultMatchers.model().attribute("account", accountModel))
                .andExpect(content().string(containsString("Account details")));
    }
}
