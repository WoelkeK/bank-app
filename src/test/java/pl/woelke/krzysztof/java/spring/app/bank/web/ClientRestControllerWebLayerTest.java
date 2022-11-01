package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.woelke.krzysztof.java.spring.app.bank.service.AccountService;
import pl.woelke.krzysztof.java.spring.app.bank.service.ClientService;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ClientRestControllerWebLayerTest {

    private static final long CLIENT_ID_11 = 11L;
    public static final String CLIENT_FIRST_NAME_TEST = "test";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService clientService;
    @MockBean
    private AccountService accountService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @WithMockUser(username = "CRIS")
    void read() throws Exception {
        ClientModel clientModel = new ClientModel();
        clientModel.setFirstName("Cris");

        Mockito.when(clientService.read(CLIENT_ID_11)).thenReturn(clientModel);

        ResultActions resultActions = mockMvc.perform(
                        get("/api/clients/{id}", CLIENT_ID_11).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        MockHttpServletResponse mvcResultResponse = mvcResult.getResponse();
        String contentAsString = mvcResultResponse.getContentAsString();
        System.out.println("ResponseBody: " + contentAsString);
    }

    @Test
    @WithMockUser(username = "CRIS")
    void create() throws Exception {
        // given
        ClientModel clientModel = new ClientModel();
        clientModel.setFirstName(CLIENT_FIRST_NAME_TEST);
        // when
        Mockito.when(clientService.create(clientModel)).thenReturn(clientModel);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/clients/"))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult mvcResult = resultActions.andReturn();
        MockHttpServletResponse mvcResultResponse = mvcResult.getResponse();
        String contentAsString = mvcResultResponse.getContentAsString();
        System.out.println("ResponseBody: " + contentAsString);

        // then
//        Assertions.assertAll(
//                () -> Assertions.assertFalse(contentAsString.isEmpty()),
//                () -> Assertions.assertTrue(contentAsString.contains("test"))
//        );
    }
}