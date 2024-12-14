package DealerShop.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity(name = "CARBRAND_PLAN")
public class CarbrandPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String name;

    private Long max_users;

    private BigDecimal price;

    @OneToMany(mappedBy = "carbrandPlan")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Set<Carbrand> carbrands;
}
