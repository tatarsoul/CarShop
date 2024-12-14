package DealerShop.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;


@Entity(name="ROLES")
@Data

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT", unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Set<User> users;
}
