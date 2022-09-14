package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.woelke.krzysztof.java.spring.app.bank.service.AccountService;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/accounts")
public class AccountController {

    private static final Logger LOGGER = Logger.getLogger(AccountController.class.getName());

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String listView(ModelMap modelMap) {
        LOGGER.info("listView()");
        List<AccountModel> accounts = accountService.list();
        modelMap.addAttribute("accounts", accounts);

        return "list-accounts.html";
    }

    @GetMapping(value = "/create")
    public String createView(ModelMap modelMap) {
        LOGGER.info("createView()");
        modelMap.addAttribute("account", new AccountModel());

        return "create-account.html";
    }

    @PostMapping(value = "/create")
    public String create(
            @ModelAttribute(name = "account") AccountModel accountModel) {
        LOGGER.info("create(" + accountModel + ")");
        accountService.create(accountModel);
        return "redirect:/accounts";
    }

    @GetMapping(value = "/read/{id}")
    public String read(
            @PathVariable(name = "id") Long id,
            ModelMap modelMap) throws Exception {
        LOGGER.info("read(" + id + ")");
        AccountModel accountModel = accountService.read(id);
        modelMap.addAttribute("account", accountModel);
        return "read-account.html";
    }


    @GetMapping(value = "/update/{id}")
    public String updateView(
            @PathVariable(name = "id") Long id,
            ModelMap modelMap) throws Exception {
        LOGGER.info("updateView()");
        AccountModel accountModel = accountService.read(id);
        modelMap.addAttribute("account", accountModel);
        return "update-account.html";
    }

    @PostMapping(value = "/update")
    public String update(
            @ModelAttribute(name = "account") AccountModel accountModel) {
        LOGGER.info("update(" + accountModel+")");
        accountService.update(accountModel);
        return "redirect:/accounts";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(
            @PathVariable(name = "id") Long id) {
        LOGGER.info("delete(" + id + ")");
//        return "list-accounts";
        accountService.delete(id);
        return "redirect:/accounts";
    }
}
