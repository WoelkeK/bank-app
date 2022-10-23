package pl.woelke.krzysztof.java.spring.app.bank.service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.ClientEntity;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ClientMapperTest {

    @Autowired
    ClientMapper clientMapper;

    @Test
    void listModels() {
        // given
        List<ClientEntity> clientEntities = new ArrayList<>();
        // when
        List<ClientModel> clientModels = clientMapper.listModels(clientEntities);
        // then
        Assertions.assertNotNull(clientModels,"clientModels is null");
    }

    @Test
    void entityToModel() {
        // given
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setLastName("test");
        // when
        ClientModel clientModel = clientMapper.entityToModel(clientEntity);
        // then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(clientModel, "clientModel is null"),
                () -> Assertions.assertEquals(clientEntity.getLastName(), clientModel.getLastName())
        );
    }

    @Test
    void modelToEntity() {
        // given
        ClientModel clientModel = new ClientModel();
        clientModel.setFirstName("test");
        // when
        ClientEntity clientEntity = clientMapper.modelToEntity(clientModel);
        // then


        Assertions.assertAll(
                () -> Assertions.assertNotNull(clientEntity, "clientEntity is null"),
                () -> Assertions.assertEquals(clientEntity.getFirstName(), clientModel.getFirstName())
        );
    }
}