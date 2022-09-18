package pl.woelke.krzysztof.java.spring.app.bank.service;

import org.springframework.stereotype.Service;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.AccountNotFoundException;
import pl.woelke.krzysztof.java.spring.app.bank.repository.ClientRepository;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.ClientEntity;
import pl.woelke.krzysztof.java.spring.app.bank.service.mapper.ClientMapper;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ClientService {
    private static final Logger LOGGER = Logger.getLogger(ClientService.class.getName());

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientModel> list() {
        LOGGER.info("list()");
        List<ClientEntity> clientEntities = clientRepository.findAll();
        List<ClientModel> clientModels = clientMapper.listModels(clientEntities);
        return clientModels;
    }

    public void create(ClientModel clientModel) {
        LOGGER.info("create(" + clientModel + ")");
//        ClientEntity clientEntity = new ClientEntity();
//        clientEntity.setFirstName("Jozef");
//        clientEntity.setLastName("Kowlaski");
//        clientRepository.save(clientEntity);
//        ClientEntity clientEntity = clientMapper.
        ClientEntity clientEntity = clientMapper.modelToEntity(clientModel);
        clientRepository.save(clientEntity);
    }

    public ClientModel read(Long id) throws Exception {
        Optional<ClientEntity> optionalClientEntity = clientRepository.findById(id);
        ClientEntity clientEntity = optionalClientEntity.orElseThrow(
                () -> new AccountNotFoundException("Brak klienta o podanym id " + id)
        );
        ClientModel clientModel = clientMapper.entityToModel(clientEntity);
        LOGGER.info("read(...)" + clientEntity);
        return clientModel;
    }

    public void update(ClientModel clientModel) {
        LOGGER.info("update()" + clientModel);
        ClientEntity clientEntity = clientMapper.modelToEntity(clientModel);
        clientRepository.save(clientEntity);

    }

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");
        clientRepository.deleteById(id);
    }
}
