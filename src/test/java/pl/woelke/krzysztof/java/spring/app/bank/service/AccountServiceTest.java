package pl.woelke.krzysztof.java.spring.app.bank.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.AccountEntity;
import pl.woelke.krzysztof.java.spring.app.bank.service.mapper.AccountMapper;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;


@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountMapper accountMapper;


    @Test
    void create() {
        // given
        AccountModel accountModel = new AccountModel();
        // when
        AccountEntity accountEntity = accountMapper.modelToEntity(accountModel);
        // then
        Assertions.assertNotNull(accountEntity,"accountEntity is null");
    }


}