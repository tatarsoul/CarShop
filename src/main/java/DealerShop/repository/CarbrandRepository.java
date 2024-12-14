package DealerShop.repository;

import DealerShop.model.Carbrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarbrandRepository extends JpaRepository<Carbrand, Long> {
    @Query("SELECT c FROM CARBRAND c WHERE concat(c.fio, ' ', c.dealershop.name, ' ', c.carbrandPlan.name, ' ', c.start_date, ' ', c.end_date) LIKE %?1%")
    List<Carbrand> search(String keyword);

    @Query("SELECT d.name, COUNT(c) " +
            "FROM CARBRAND c " +
            "JOIN c.dealershop d " +
            "GROUP BY d.name")
    List<Object[]> countByDealershop();

    @Query("SELECT cbp.name, COUNT(c) FROM CARBRAND c JOIN c.carbrandPlan cbp GROUP BY cbp.name")
    List<Object[]> countCarbrandsByCarbrandPlan();
}
