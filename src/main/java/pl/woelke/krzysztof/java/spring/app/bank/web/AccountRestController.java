package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.AccountNotFoundException;
import pl.woelke.krzysztof.java.spring.app.bank.service.AccountService;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/accounts")
public class AccountRestController {

    private static final Logger LOGGER = Logger.getLogger(AccountRestController.class.getName());

    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }


    // C - create
    @PostMapping
    public AccountModel create(@RequestBody AccountModel accountModel) {
        LOGGER.info("create()" + accountModel);
        AccountModel createdAccountModel = accountService.create(accountModel);
        return createdAccountModel;
    }

    // R - read
    @GetMapping(value = "/{id}")
    public AccountModel read(
            @PathVariable(name = "id") Long id) throws AccountNotFoundException {
        LOGGER.info("read(" + id + ")");
        AccountModel readedAccountModel = accountService.read(id);
        return readedAccountModel;
    }

    // U - update
    @PutMapping(value = "/{id}")
    public AccountModel update(
            @RequestBody AccountModel accountModel) throws AccountNotFoundException {
        LOGGER.info("update(" + accountModel + ")");
        AccountModel updatedAccountModel = accountService.update(accountModel);
        return updatedAccountModel;
    }

    // D - delete
    @DeleteMapping(value = "/{id}")
    public void delete(
            @PathVariable(name = "id") Long id) {
        LOGGER.info("delete(" + id + ")");
        accountService.delete(id);
    }

    // L - list
    @GetMapping
    public List<AccountModel> list() {
        LOGGER.info("list()");
        List<AccountModel> accounts = accountService.list();
        return accounts;
    }
}

// TODO: 27.10.2022 zaimplementować metody crud 