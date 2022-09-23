package pl.woelke.krzysztof.java.spring.app.bank.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CLIENT_ROLES")
public class ClientRoleEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<ClientEntity> clients = new ArrayList<>();

    public ClientRoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClientEntity> getClients() {
        return clients;
    }

    public void setClients(List<ClientEntity> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "ClientRoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
