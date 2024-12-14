package DealerShop.repository;

import DealerShop.model.CarbrandPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarbrandPlanRepository extends JpaRepository<CarbrandPlan, Long> {
    @Query("SELECT cp FROM CARBRAND_PLAN cp WHERE CONCAT(cp.id, ' ', cp.name, ' ', cp.max_users, ' ', cp.price) LIKE %?1%")
    List<CarbrandPlan> search(String keyword);
}
