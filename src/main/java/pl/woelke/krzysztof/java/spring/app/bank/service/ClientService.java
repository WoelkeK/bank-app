package pl.woelke.krzysztof.java.spring.app.bank.service;

import org.springframework.stereotype.Service;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.ClientNotFoundException;
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
        LOGGER.info("clientModels: " + clientModels);
        return clientModels;
    }

    public ClientModel create(ClientModel clientModel) {
        LOGGER.info("create(" + clientModel + ")");
        ClientEntity clientEntity = clientMapper.modelToEntity(clientModel);
        ClientEntity savedClientEntity = clientRepository.save(clientEntity);
        ClientModel  savedClientModel = clientMapper.entityToModel(savedClientEntity);
        return savedClientModel;
    }

    public ClientModel read(Long id) throws ClientNotFoundException {
        LOGGER.info("read(" + id + ")");
        Optional<ClientEntity> optionalClientEntity = clientRepository.findById(id);
        ClientEntity clientEntity = optionalClientEntity.orElseThrow(
                () -> new ClientNotFoundException("Brak klienta o podanym id " + id)
        );
        ClientModel clientModel = clientMapper.entityToModel(clientEntity);
        LOGGER.info("read(...)" + clientModel);
        return clientModel;
    }

    public ClientModel update(ClientModel clientModel) {
        LOGGER.info("update()" + clientModel);
        ClientEntity clientEntity = clientMapper.modelToEntity(clientModel);
        ClientEntity updatedClientEntity = clientRepository.save(clientEntity);
        ClientModel updatedClientModel = clientMapper.entityToModel(updatedClientEntity);
        LOGGER.info("update()"+updatedClientModel);
        return updatedClientModel;

    }

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");
        clientRepository.deleteById(id);
    }
}
// TODO: 13.10.2022 add tests for the whole accountService methods.