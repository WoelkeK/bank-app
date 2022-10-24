package pl.woelke.krzysztof.java.spring.app.bank.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
class BankUserDetailsServiceTest {

    @Autowired
    private BankUserDetailsService bankUserDetailsService;

    @Test
    void loadUserByUsername() {
        // given
        String username =null;
        // when

        // then
        Assertions.assertThrows(UsernameNotFoundException.class,()->bankUserDetailsService.loadUserByUsername(username),"No exeption with wrong username");
    }
}