package DealerShop.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;


@Entity(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(name = "login", columnDefinition = "TEXT", unique = true)
    private String login;

    @Column(columnDefinition = "TEXT")
    private String pass;

    @Column(columnDefinition = "TEXT", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Role role;

    @OneToMany(mappedBy = "user")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Set<CarbrandLog> carbrandLogs;
}
