package pl.woelke.krzysztof.java.spring.app.bank.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.ClientNotFoundException;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.ClientEntity;
import pl.woelke.krzysztof.java.spring.app.bank.service.mapper.ClientMapper;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.ClientModel;

import java.util.List;

@SpringBootTest
class ClientServiceTest {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;

    @Test
    void list() {
        // given
        Long id = 1L;
        ClientModel clientModel = new ClientModel();
        clientModel.setId(id);
        clientModel.setFirstName("test");
        ClientModel createdClientModel = clientService.create(clientModel);
        // when
        List<ClientModel> listClientModels = clientService.list();
        // then
        Assertions.assertAll(
                () -> Assertions.assertFalse(listClientModels.isEmpty()),
                () -> Assertions.assertTrue(listClientModels.size() > 0),
                () -> Assertions.assertTrue(listClientModels.stream().anyMatch(item -> "test".equals(item.getFirstName())))
        );
    }

    @Test
    void create() {
// given
        ClientModel clientModel = new ClientModel();
// when
        ClientEntity clientEntity = clientMapper.modelToEntity(clientModel);
// then
        Assertions.assertNotNull(clientEntity, "clientEntity is null");
    }

    @Test
    void read() throws Exception {

        // given
        Long id = -999L;
        // when
        // then
        Assertions.assertThrows(ClientNotFoundException.class, () -> clientService.read(id), "No exception with wrong account id");
    }

    @Test
    void update() throws ClientNotFoundException {

        // given
        ClientModel clientModel = new ClientModel();
        clientModel.setFirstName("test");
        ClientModel createdClientModel = clientService.create(clientModel);
        // when
        createdClientModel.setFirstName("updatedTest");
        ClientModel updatedClientModel = clientService.update(createdClientModel);

        // then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(updatedClientModel, "clientModel is null"),
                () -> Assertions.assertEquals(updatedClientModel.getId(), clientModel.getId())
        );
    }

    @Test
    void delete() throws ClientNotFoundException {

        // given
        ClientModel clientModel = new ClientModel();

        // when
        ClientModel createdClientModel = clientService.create(clientModel);
        Long createdClientModelId = createdClientModel.getId();
        clientService.delete(createdClientModelId);

        // then
        Assertions.assertThrows(ClientNotFoundException.class,
                () -> clientService.read(createdClientModelId), "clientModel not deleted");
    }
}
