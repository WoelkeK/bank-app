package pl.woelke.krzysztof.java.spring.app.bank.service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.AccountEntity;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

class AccountMapperTest {

    public static final String ACCOUNT_NUMBER = "PL1234567890";

    @Test
    void modelToEntity() {
        // given
        AccountMapper accountMapper = new AccountMapper();
        AccountModel accountModel = new AccountModel();
        accountModel.setNumber(ACCOUNT_NUMBER);
        // when
        AccountEntity accountEntity = accountMapper.modelToEntity(accountModel);
        // then
        Assertions.assertEquals(ACCOUNT_NUMBER, accountEntity.getNumber(),"account numbers are not equal");
    }

    @Test
    void entityToModel() {
        // given
        AccountMapper accountMapper = new AccountMapper();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setNumber(ACCOUNT_NUMBER);

        // when
        AccountModel accountModel = accountMapper.entityToModel(accountEntity);
        // then
        Assertions.assertEquals(ACCOUNT_NUMBER, accountModel.getNumber(),"account numbers are not equal");
    }

    //test
}
