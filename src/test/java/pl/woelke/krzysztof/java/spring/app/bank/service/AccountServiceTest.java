package pl.woelke.krzysztof.java.spring.app.bank.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.AccountNotFoundException;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

import java.util.List;


@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void list() {
        // given

        // when
        List<AccountModel> accountModels = accountService.list();
        // then

        Assertions.assertNotNull(accountModels, "accoundModels is null");
    }

    @Test
    void create() throws AccountNotFoundException {
        // given
        AccountModel accountModel = new AccountModel();
        accountModel.setNumber("test");
        // when
        AccountModel createdAccountModel = accountService.create(accountModel);
        AccountModel readedAccountModel = accountService.read(createdAccountModel.getId());
        // then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdAccountModel, "accountEntity is null"),
                () -> Assertions.assertEquals(readedAccountModel.getNumber(), accountModel.getNumber())
        );
    }

    @Test
    void read() {
        // given

        // when

        // then
        Assertions.assertThrows(AccountNotFoundException.class, () -> accountService.read(-999L), "No exception with wrong account id");
    }

    @Test
    void update() throws AccountNotFoundException {

        AccountModel accountModel = new AccountModel();
        accountModel.setNumber("testNumber");
        AccountModel createdAccountModel = accountService.create(accountModel);
        AccountModel setReadAccountModel = accountService.read(createdAccountModel.getId());

        // when
        setReadAccountModel.setNumber("updatedNumber");
        AccountModel updatedAccountModel = accountService.update(setReadAccountModel);
        AccountModel checkReadAccountModel = accountService.read(updatedAccountModel.getId());

        // then
        Assertions.assertAll(

                () -> Assertions.assertNotNull(checkReadAccountModel, "account is null"),
                () -> Assertions.assertNotEquals(checkReadAccountModel.getNumber(), accountModel.getNumber(), "update fail"));
    }

    @Test
    void delete() throws AccountNotFoundException {
        // given
        AccountModel accountModel = new AccountModel();
        accountModel.setNumber("testNumber");
        AccountModel createdAccountModel = accountService.create(accountModel);
        accountService.read(createdAccountModel.getId());
        // when
        accountService.delete(createdAccountModel.getId());
        // then
        Assertions.assertThrows(AccountNotFoundException.class, () -> accountService.read(createdAccountModel.getId()), "Account not deleted");
    }

    @Test
    void convertCurrency() throws Exception {
        // given
        String currency = "USD";
        AccountModel accountModel = new AccountModel();

        // when
        AccountModel convertedCurrency = accountService.convertCurrency(accountModel, currency);

        // then
        Assertions.assertNotNull(convertedCurrency, "convertedCurrency is null");
    }
}

// TODO: 13.10.2022 test missing crud methods.