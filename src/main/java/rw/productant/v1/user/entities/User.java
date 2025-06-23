package rw.productant.v1.user.entities;

import lombok.Getter;
import lombok.Setter;
import rw.productant.v1.user.enums.EAccountStatus;
import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String email;

    private String password;

    private EAccountStatus status = EAccountStatus.ACTIVE;

    public User() {
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
