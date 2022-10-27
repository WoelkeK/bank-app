package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.woelke.krzysztof.java.spring.app.bank.service.AccountService;
import pl.woelke.krzysztof.java.spring.app.bank.service.ClientService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
// TEST - test endpontów w kontrolerach.
// NIE uruchamia server HTTP i i testuje tylko warstwe web, reszta wartsw zamockowana
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountService accountService;
	@MockBean
	private ClientService clientService;
	@MockBean
	private PasswordEncoder passwordEncoder;

	@Test
	@WithMockUser(username = "CRIS")
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/accounts")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Accounts List")));
	}

}