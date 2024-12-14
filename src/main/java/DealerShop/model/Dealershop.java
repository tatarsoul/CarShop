package DealerShop.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity(name = "DEALERSHOP")
@Data
public class Dealershop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String contact;

    @Column(columnDefinition = "TEXT")
    private String descr;

    @OneToMany(mappedBy = "dealershop")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Set<Carbrand> carbrands;
}
