package pl.woelke.krzysztof.java.spring.app.bank.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientRestControllerWebAppTest {

    private static final long CLIENT_ID_11 = 11L;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "CRIS")
    void clientModelList() throws Exception {
// given
        ObjectMapper objectMapper = new ObjectMapper();
// when
        ResultActions resultActions = mockMvc.perform(get("/api/clients")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        MockHttpServletResponse mvcResultResponse = mvcResult.getResponse();
        String contentAsString = mvcResultResponse.getContentAsString();
        List<ClientModel> clientModelList = objectMapper.readValue(contentAsString, new TypeReference<List<ClientModel>>() {
        });

// then
        Assertions.assertAll(
                () -> Assertions.assertTrue(clientModelList.size() > 0),
                () -> Assertions.assertNotNull(clientModelList.get(0))
        );
    }


    @Test
    @WithMockUser(username = "CRIS")
    void read() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/clients/{id}", CLIENT_ID_11))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        MockHttpServletResponse mvcResultResponse = mvcResult.getResponse();
        String contentAsString = mvcResultResponse.getContentAsString();
        ClientModel clientModel = objectMapper.readValue(contentAsString, ClientModel.class);

        // then
        Assertions.assertEquals(CLIENT_ID_11, clientModel.getId(), "clientModel IDs not EQUALS");
    }

    @Test
    @WithMockUser(username = "CRIS")
    void create() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        ClientModel clientModel = new ClientModel();
        clientModel.setFirstName("test");
        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/clients")
                .content(objectMapper.writeValueAsString(clientModel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        MvcResult mvcResult = resultActions.andReturn();
        MockHttpServletResponse mvcResultResponse = mvcResult.getResponse();
        String contentString = mvcResultResponse.getContentAsString();
        ClientModel createdClientModel = objectMapper.readValue(contentString, ClientModel.class);

        // then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdClientModel),
                () -> Assertions.assertEquals(clientModel.getFirstName(), createdClientModel.getFirstName())
        );
    }

    @Test
    @WithMockUser(username = "CRIS")
    void update() throws Exception {
// given
        ObjectMapper objectMapper = new ObjectMapper();
        ClientModel clientModel = new ClientModel();
        clientModel.setFirstName("updatedTest");
        clientModel.setId(CLIENT_ID_11);
// when

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/clients/{id}", CLIENT_ID_11)
                        .content(objectMapper.writeValueAsString(clientModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        MockHttpServletResponse mvcResultResponse = mvcResult.getResponse();
        String contentAsString = mvcResultResponse.getContentAsString();
        ClientModel updatedClientModel = objectMapper.readValue(contentAsString, ClientModel.class);

// then
        Assertions.assertAll(
                () -> Assertions.assertEquals(clientModel.getId(), updatedClientModel.getId()),
                () -> Assertions.assertEquals(clientModel.getFirstName(), updatedClientModel.getFirstName())
        );
    }

    @Test
    @WithMockUser(username = "CRIS")
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/clients/{id}", 1))
                .andExpect(status().isOk());
            }
}
// TODO: 01.11.2022 Update test intergalny (utworzyc i update i check
// w delete na koncu check i spr rzucanie wyjatku ClientNotFoundException