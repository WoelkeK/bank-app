package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.woelke.krzysztof.java.spring.app.bank.api.external.chucknorrisjokes.ChuckNorrisJoke;
import pl.woelke.krzysztof.java.spring.app.bank.service.ChuckNorrisJokeService;
import pl.woelke.krzysztof.java.spring.app.bank.service.ClientService;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/clients")
public class ClientController {
    private static final Logger LOGGER = Logger.getLogger(ClientController.class.getName());

    //    @Autowired
    private ClientService clientService;
    private PasswordEncoder passwordEncoder;
    private ChuckNorrisJokeService chuckNorrisJokeService;

    public ClientController(ClientService clientService, PasswordEncoder passwordEncoder, ChuckNorrisJokeService chuckNorrisJokeService) {
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
        this.chuckNorrisJokeService = chuckNorrisJokeService;
    }

    @GetMapping
    public String listView(ModelMap modelMap) throws IOException {
        LOGGER.info("listView()");
        List<ClientModel> clients = clientService.list();
        ChuckNorrisJoke chuckNorrisJoke = chuckNorrisJokeService.read();
        modelMap.addAttribute("clients", clients);
        modelMap.addAttribute("chuckNorrisJoke", chuckNorrisJoke);
        return "list-clients.html";
    }

    @GetMapping(value = "/create")
    public String createView(ModelMap modelMap) {
        LOGGER.info("createView()");
        modelMap.addAttribute("clients", new ClientModel());
        return "create-client.html";
    }

    @PostMapping(value = "/create")
//    public String create(String firstName, String lastName) {
    public String create(
            @ModelAttribute(name = "client") ClientModel clientModel) {
        LOGGER.info("create(" + clientModel + ")");
//        LOGGER.info("create(" + lastName + ")");
        clientModel.setPassword(passwordEncoder.encode(clientModel.getPassword()));
        clientService.create(clientModel);
        return "redirect:/clients";
    }

    @GetMapping(value = "/read/{id}")
    public String read(
            @PathVariable(name = "id") Long id,
            ModelMap modelMap) throws Exception {
        LOGGER.info("read(" + id + ")");
        ClientModel clientModel = clientService.read(id);
        modelMap.addAttribute("client", clientModel);
        return "read-clients.html";
    }

    @GetMapping(value = "/update/{id}")
    public String updateView(
            @PathVariable(name = "id") Long id,
            ModelMap modelMap) throws Exception {
        LOGGER.info("updateView()");
        ClientModel clientModel = clientService.read(id);
        modelMap.addAttribute("client", clientModel);
        return "update-client.html";
    }

    @PostMapping(value = "/update")
    public String update(
            @ModelAttribute(name = "client") ClientModel clientModel) {
        LOGGER.info("update()");
        clientService.update(clientModel);
        return "redirect:/clients";

    }

    @GetMapping(value = "/delete/{id}")
    public String delete(
            @PathVariable(name = "id") Long id) {
        LOGGER.info("delete()");
        clientService.delete(id);
        return "redirect:/clients";

    }
}
