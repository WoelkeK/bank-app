package pl.woelke.krzysztof.java.spring.app.bank.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.ClientNotFoundException;
import pl.woelke.krzysztof.java.spring.app.bank.service.ClientService;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientRestController {
    private static final Logger LOGGER = Logger.getLogger(ClientRestController.class.getName());

    private ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientModel> list(){
        LOGGER.info("clientModelList()");
        List<ClientModel> clientModels = clientService.list();
        return clientModels;
    }

    @PostMapping
    public ClientModel create(@RequestBody ClientModel clientModel) {
        LOGGER.info("create()" + clientModel);
        ClientModel createdClientModel = clientService.create(clientModel);
        return createdClientModel;
    }

    @GetMapping(value = "/{id}")
    public ClientModel read(@PathVariable(name = "id") Long id) throws ClientNotFoundException {
        LOGGER.info("read(" + id + ")");
        ClientModel clientModel = clientService.read(id);
        LOGGER.info("read(...)" + clientModel);
        return clientModel;
    }

    @PutMapping("/{id}")
    public ClientModel update(@PathVariable(name = "id") Long id, @RequestBody ClientModel clientModel) {
        LOGGER.info("update(" + clientModel + " id " + id +")" );
        clientModel.setId(id);
        ClientModel updatedClientModel = clientService.update(clientModel);
        return updatedClientModel;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        LOGGER.info("delete()");
        clientService.delete(id);
    }
}
