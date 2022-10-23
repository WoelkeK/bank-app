package pl.woelke.krzysztof.java.spring.app.bank.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.ClientEntity;

import java.util.Optional;

@SpringBootTest
class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void findByLogin() {
        // given
        String login = "test";
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setLogin(login);
        clientRepository.save(clientEntity);
        // when
        ClientEntity foundClientEntity = clientRepository.findByLogin(login);
        // then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(foundClientEntity, "Login not found"),
                () -> Assertions.assertEquals(foundClientEntity.getLogin(), clientEntity.getLogin())
        );
    }

    @Test
    void create() {
        // given
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("test");
        // when
        ClientEntity createdClientEntity = clientRepository.save(clientEntity);
        // then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdClientEntity, " createdClientEnity is null"),
                () -> Assertions.assertEquals(createdClientEntity.getFirstName(), clientEntity.getFirstName())
        );
    }

    @Test
    void read() {
// given
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setLastName("test");
        clientRepository.save(clientEntity);
// when
        Optional<ClientEntity> readedClientEntity = clientRepository.findById(clientEntity.getId());
// then
        Assertions.assertNotNull(readedClientEntity, "readedClientEntity is null");
    }

    @Test
    void update() {
        // given
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setLastName("test");
        ClientEntity createdClientEntity = clientRepository.save(clientEntity);
        clientEntity.setLastName("updatedTest");
        // when
        Optional<ClientEntity> updatedClientEntity = clientRepository.findById(createdClientEntity.getId());
        // then
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(updatedClientEntity.get().getLastName(), createdClientEntity.getLastName()),
                () -> Assertions.assertNotNull(updatedClientEntity.get().getLastName())
        );

    }

    @Test
    void delete() {

        // given
        ClientEntity clientEntity = new ClientEntity();
        ClientEntity createdClientEntity = clientRepository.save(clientEntity);
        // when
        clientRepository.deleteById(createdClientEntity.getId());
        // then
        Assertions.assertEquals(clientRepository.findById(createdClientEntity.getId()), Optional.empty(),"clientEntity not deleted");
    }
}