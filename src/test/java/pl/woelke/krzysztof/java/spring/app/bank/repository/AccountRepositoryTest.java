package pl.woelke.krzysztof.java.spring.app.bank.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.AccountEntity;
import java.util.Optional;

@SpringBootTest
class AccountRepositoryTest {
    private static final String INIT_NUMBER = "PL0123456789";
    private static final String UPDATED_NUMBER = "DE9876543210";

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void create() {
        // given
        AccountEntity accountEntity = new AccountEntity();
        // when
        AccountEntity savedAccountEntity = accountRepository.save(accountEntity);
        // then
        Assertions.assertNotNull(savedAccountEntity, "savedAccountEntity is null");
    }

    @Test
    void read() {

        // given
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setNumber(INIT_NUMBER);
        // when
        accountRepository.save(accountEntity);
        Optional<AccountEntity> readedEntity = accountRepository.findById(accountEntity.getId());
        // then
        Assertions.assertNotNull(readedEntity, "readedAccountEntity is null");
    }

    @Test
    void update() {
        // given
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setNumber(INIT_NUMBER);
        accountRepository.save(accountEntity);
        // when
        accountEntity.setNumber(UPDATED_NUMBER);
        accountRepository.save(accountEntity);
        Optional<AccountEntity> updatedEntity = accountRepository.findById(accountEntity.getId());
        // then
        Assertions.assertEquals((updatedEntity.get()).getNumber(), UPDATED_NUMBER);
    }

    @Test
    void delete() {
        // given
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setNumber("DELETE_NUMBER");
        accountRepository.save(accountEntity);
        // when
        accountRepository.delete(accountEntity);
        Optional<AccountEntity> deletedEntity = accountRepository.findById(accountEntity.getId());
        // then
        Assertions.assertTrue(deletedEntity.isEmpty());
    }
}

// TODO: 31.08.2022 uzupelnic metody pozostale testowe dla crud
// Zrobi test jednostkowy dla serwisu