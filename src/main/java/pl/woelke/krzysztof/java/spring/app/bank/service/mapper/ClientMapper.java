package pl.woelke.krzysztof.java.spring.app.bank.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.AccountEntity;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.ClientEntity;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
    private static final Logger LOGGER = Logger.getLogger(ClientMapper.class.getName());

    private ClientEntity clientEntity;

    public List<ClientModel> listModels(List<ClientEntity> clientEntities) {
        LOGGER.info("list()" + clientEntities);

        List<ClientModel> clientModels = clientEntities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
        return clientModels;
    }

    public ClientModel entityToModel(ClientEntity clientEntity) {
        LOGGER.info("entityToModel" + clientEntity);
        ModelMapper modelMapper = new ModelMapper();
        ClientModel clientModel = modelMapper.map(clientEntity, ClientModel.class);
        return clientModel;
    }

    public ClientEntity modelToEntity(ClientModel clientModel) {
        LOGGER.info("modelToEntity()" + clientModel);
        ModelMapper modelMapper = new ModelMapper();
        ClientEntity clientEntity = modelMapper.map(clientModel, ClientEntity.class);
        return clientEntity;
    }
}
