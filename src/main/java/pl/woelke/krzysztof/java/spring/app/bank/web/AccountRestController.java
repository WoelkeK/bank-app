package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public void create() {
    }

    // R - read
    public void read() {
    }

    // U - update
    public void update() {
    }

    // D - delete
    public void delete() {
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