package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
//@WebMvcTest(controllers = AccountController.class)
@AutoConfigureWebMvc
class AccountControllerTest {

    String endPoint = "/accounts";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAccountsList() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        Assertions.assertEquals(200, status);
    }
}