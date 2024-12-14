package DealerShop.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "CARBRAND")
@Data
public class Carbrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String fio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;

    @OneToMany(mappedBy = "carbrand")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Set<CarbrandLog> carbrandLogs;

    @ManyToOne
    @JoinColumn(name = "dealershop_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Dealershop dealershop;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "carbrandPlan_id", referencedColumnName = "id")
    private CarbrandPlan carbrandPlan;

    @Override
    public String toString() {
        return "{" +
                "ID=" + id +
                ", Key='" + fio + '\'' +
                ", Dealershop=" + (dealershop != null ? dealershop.getName() : "null") +
                ", CarbrandPlan=" + (carbrandPlan != null ? carbrandPlan.getName() : "null") +
                ", StartDate=" + start_date +
                ", EndDate=" + end_date +
                '}';
    }
}
