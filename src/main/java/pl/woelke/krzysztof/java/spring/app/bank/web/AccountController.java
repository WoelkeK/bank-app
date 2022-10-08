package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.NbpApiClient;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.nbp.model.Currency;
import pl.woelke.krzysztof.java.spring.app.bank.service.AccountService;
import pl.woelke.krzysztof.java.spring.app.bank.service.ClientService;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/accounts")
public class AccountController {

    private static final Logger LOGGER = Logger.getLogger(AccountController.class.getName());

    private AccountService accountService;
    private ClientService clientService;
    private NbpApiClient nbpApiClient;

    public AccountController(AccountService accountService, ClientService clientService, NbpApiClient nbpApiClient) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.nbpApiClient = nbpApiClient;
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

    @PostMapping(value = "/convert")
    public String convert(
            String currency,
            ModelMap modelMap) throws Exception {
        LOGGER.info("convert(" + currency + ")");
        Currency rate = nbpApiClient.getRates(currency);
        LOGGER.info("rate:" + rate);
//        AccountModel accountModel = accountService.read(currency);
        modelMap.addAttribute("account", new AccountModel());
        return "read-account.html";
    }
}
// TODO: 07.10.2022 metoda convert musi przyjmować id tak jak metoda read.
//za pomocą id pobrac szczególy konta
//ze szczegółów konta pobrac saldo
//pobrane saldo podzielic przez rate pochodzący z currency(api)
