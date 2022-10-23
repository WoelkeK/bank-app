package pl.woelke.krzysztof.java.spring.app.bank.service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.AccountEntity;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

import java.util.ArrayList;
import java.util.List;

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
        Assertions.assertEquals(ACCOUNT_NUMBER, accountEntity.getNumber(), "account numbers are not equal");
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
        Assertions.assertEquals(ACCOUNT_NUMBER, accountModel.getNumber(), "account numbers are not equal");
    }

    @Test
    void listModels() {
        // given
        List<AccountEntity> accountEntities = new ArrayList<>();
        AccountMapper accountMapper = new AccountMapper();
        // when
        List<AccountModel> accountModels = accountMapper.listModels(accountEntities);
        // then
        Assertions.assertNotNull(accountModels, "accountModels is null");
    }

    @Test
    void listEntities() {
        // given
        List<AccountModel> accountModels = new ArrayList<>();
        AccountMapper accountMapper = new AccountMapper();
        // when
        List<AccountEntity> accountEntities = accountMapper.listEntities(accountModels);
        // then
        Assertions.assertNotNull(accountEntities,"accountEntities is null");
    }

    //test
}
// TODO: 13.10.2022 extend existing test by checking other account use