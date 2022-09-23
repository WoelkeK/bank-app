package pl.woelke.krzysztof.java.spring.app.bank.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
public class ClientEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ClientRoleEntity> roles = new ArrayList<>();

    public ClientEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ClientRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<ClientRoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
