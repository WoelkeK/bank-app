package pl.woelke.krzysztof.java.spring.app.bank.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.AccountNotFoundException;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.AccountEntity;
import pl.woelke.krzysztof.java.spring.app.bank.service.mapper.AccountMapper;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;


@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountService accountService;

    @Test
    void create() {
        // given
        AccountModel accountModel = new AccountModel();
        // when
        AccountEntity accountEntity = accountMapper.modelToEntity(accountModel);
        // then
        Assertions.assertNotNull(accountEntity, "accountEntity is null");
    }

    @Test
    void read() {
        // given

        // when

        // then
        Assertions.assertThrows(AccountNotFoundException.class, () -> accountService.read(-999L),"No exception with wrong account id");

    }
}

// TODO: 13.10.2022 test missing crud methods.