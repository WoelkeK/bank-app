package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.woelke.krzysztof.java.spring.app.bank.service.AccountService;
import pl.woelke.krzysztof.java.spring.app.bank.service.ClientService;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/accounts")
//@SessionAttributes(names = {"account"})
public class AccountController {

    private static final Logger LOGGER = Logger.getLogger(AccountController.class.getName());

    private AccountService accountService;
    private ClientService clientService;


    public AccountController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
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
        List<ClientModel> clients = clientService.list();
        modelMap.addAttribute("clients", clients);
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
        LOGGER.info("update(" + accountModel + ")");
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

    @PostMapping(value = "/convert/{id}")
    public String convert(
            @PathVariable(name = "id") Long id,
            String currency,
            ModelMap modelMap) throws Exception {
        LOGGER.info("convert(" + currency + ", " + id + ")");

        AccountModel accountModel = accountService.read(id);
        LOGGER.info("accountModel " + accountModel);
        AccountModel convertedAccountModel = accountService.convertCurrency(accountModel, currency);
        LOGGER.info("convertedAccountModel " + convertedAccountModel);
//        modelMap.addAttribute("account", convertedAccountModel);

        modelMap.addAttribute("account", convertedAccountModel);
//        return "redirect:/accounts/convert/" + id;
//        return "redirect:/accounts/read/" + id;
        return "read-account.html";
    }
}
// TODO: 07.10.2022 metoda convert musi przyjmowa?? id tak jak metoda read.
//za pomoc?? id pobrac szczeg??ly konta
//ze szczeg??????w konta pobrac saldo
//pobrane saldo podzielic przez rate pochodz??cy z currency(api)
