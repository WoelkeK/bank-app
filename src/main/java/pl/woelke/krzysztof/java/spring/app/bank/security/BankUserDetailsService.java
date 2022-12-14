package pl.woelke.krzysztof.java.spring.app.bank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.woelke.krzysztof.java.spring.app.bank.repository.ClientRepository;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.ClientEntity;
import pl.woelke.krzysztof.java.spring.app.bank.repository.entity.ClientRoleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BankUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = Logger.getLogger(BankUserDetailsService.class.getName());

    @Autowired
    private ClientRepository clientRepository;


    @Override
//    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername(" + username + ")");
        ClientEntity user = clientRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        LOGGER.info("user: " + user);
        List <String> roleNames = new ArrayList<>();
        List<ClientRoleEntity> roles = user.getRoles();
        for (ClientRoleEntity role : roles) {
            String name = role.getName();
            roleNames.add(name);
        }
        LOGGER.info("roles: " + roles);

        UserDetails userDetails = User.withUsername(user.getLogin())

                .password(user.getPassword())
                .roles(String.valueOf(roleNames))
                .build();
        return userDetails;
    }
}
