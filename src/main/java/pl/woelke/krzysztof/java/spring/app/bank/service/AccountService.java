package pl.woelke.krzysztof.java.spring.app.bank.service;

import org.springframework.stereotype.Service;
import pl.woelke.krzysztof.java.spring.app.bank.api.exception.AccountNotFoundException;
import pl.woelke.krzysztof.java.spring.app.bank.repository.AccountRepository;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.AccountEntity;
import pl.woelke.krzysztof.java.spring.app.bank.service.converter.CurrencyConverter;
import pl.woelke.krzysztof.java.spring.app.bank.service.mapper.AccountMapper;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AccountService {
    private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
    private CurrencyConverter currencyConverter;


    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper, CurrencyConverter currencyConverter) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.currencyConverter = currencyConverter;
    }

    public List<AccountModel> list() {
        LOGGER.info("list()");
        List<AccountEntity> accountEntities = accountRepository.findAll();
        List<AccountModel> accountModels = accountMapper.listModels(accountEntities);
        return accountModels;

    }

    public void create(AccountModel accountModel) {
        LOGGER.info("create(" + accountModel + ")");

        AccountEntity accountEntity = accountMapper.modelToEntity(accountModel);
        accountRepository.save(accountEntity);

    }

    public AccountModel read(Long id) throws AccountNotFoundException {
        LOGGER.info("read(" + id + ")");
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(id);

        AccountEntity accountEntity = optionalAccountEntity.orElseThrow(
                () -> new AccountNotFoundException("Brak konta o podanym id " + id));

        AccountModel accountModel = accountMapper.entityToModel(accountEntity);
        LOGGER.info("read(...)=" + accountModel);
        return accountModel;
    }

    public void update(AccountModel accountModel) {
        LOGGER.info("update()" + accountModel);
        AccountEntity accountEntity = accountMapper.modelToEntity(accountModel);
        accountRepository.save(accountEntity);

    }

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");
        accountRepository.deleteById(id);
    }

    public AccountModel convertCurrency(AccountModel accountModel, String currency) throws IOException {
        LOGGER.info("convertCurrency(" + accountModel + " " + currency + ")");
        AccountModel convertedCurrencyModel = currencyConverter.convertCurrency(accountModel, currency);
        return convertedCurrencyModel;

    }
}
