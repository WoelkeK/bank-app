package pl.woelke.krzysztof.java.spring.app.bank.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.AccountEntity;
import pl.woelke.krzysztof.java.spring.app.bank.web.model.AccountModel;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    private static final Logger LOGGER = Logger.getLogger(AccountMapper.class.getName());

    public List<AccountModel> listModels(List<AccountEntity> accountEntities) {
        LOGGER.info("listModels(" + accountEntities + ")");
        List<AccountModel> accountModels = accountEntities.stream()
                .map(this::entityToModel)
//                .map(accountEntity -> entityToModel(accountEntity))
                .collect(Collectors.toList());

        return accountModels;
    }

    public List<AccountEntity> listEntities(List<AccountModel> accountModels) {
        LOGGER.info("listEntities(" + accountModels + ")");
        List<AccountEntity> accountEntities = accountModels.stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());

        return accountEntities;
    }

    public AccountEntity modelToEntity(AccountModel accountModel) {
        LOGGER.info("modelToEntity(" + accountModel + ")");
//        AccountEntity accountEntity = new AccountEntity();
//        accountEntity.setId(accountModel.getId());
//        accountEntity.setNumber(accountModel.getNumber()+"xxxxx");
//        accountEntity.setBalance(accountModel.getBalance());
//        accountEntity.setCurrency(accountModel.getCurrency());
        ModelMapper modelMapper = new ModelMapper();
        AccountEntity accountEntity = modelMapper.map(accountModel, AccountEntity.class);
        return accountEntity;
    }

    public AccountModel entityToModel(AccountEntity accountEntity) {
        LOGGER.info("entityToModel(" + accountEntity + ")");
//        AccountModel accountModel = new AccountModel();
//        accountModel.setNumber(accountEntity.getNumber());

        ModelMapper modelMapper = new ModelMapper();
        AccountModel accountModel = modelMapper.map(accountEntity, AccountModel.class);
        return accountModel;
    }
}
// TODO: 16.08.2022 dokończyć implementacje metody entityToModel