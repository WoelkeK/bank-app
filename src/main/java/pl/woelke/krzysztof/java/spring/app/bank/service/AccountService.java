package pl.woelke.krzysztof.java.spring.app.bank.service;

import org.springframework.stereotype.Service;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.AccountNotFoundException;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.AccountEntity;
import pl.woelke.krzysztof.java.spring.app.bank.repository.AccountRepository;
import pl.woelke.krzysztof.java.spring.app.bank.service.mapper.AccountMapper;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AccountService {
    private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public List<AccountModel> list() {
        LOGGER.info("list()");
        List<AccountEntity> accountEntities = accountRepository.findAll();
        List<AccountModel> accountModels = accountMapper.listModels(accountEntities);
        return accountModels;

    }

    public void create(AccountModel accountModel) {
        LOGGER.info("create(" + accountModel + ")");
//        AccountEntity accountEntity = new AccountEntity();
//        accountEntity.setNumber("UK1234567890");
//        accountEntity.setBalance(150);
        AccountEntity accountEntity = accountMapper.modelToEntity(accountModel);
        accountRepository.save(accountEntity);

    }

    public AccountModel read(Long id) throws Exception {
        LOGGER.info("read(" + id + ")");
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(id);
//        AccountEntity accountEntity = optionalAccountEntity.orElse(new AccountEntity());
//        AccountEntity accountEntity = optionalAccountEntity.orElseThrow();
        AccountEntity accountEntity = optionalAccountEntity.orElseThrow(
                () -> new AccountNotFoundException("Brak konta o podanym id " + id));

//        if (optionalAccountEntity.isPresent()) {
//            AccountEntity getAccountEntity = optionalAccountEntity.get();
//            if (getAccountEntity == null) {
//                getAccountEntity = new AccountEntity();
//            }
//        }

        AccountModel accountModel = accountMapper.entityToModel(accountEntity);
        LOGGER.info("read(...)=" + accountModel);
        return accountModel;
    }

    public void update() {

    }

    public void delete() {

    }
}
