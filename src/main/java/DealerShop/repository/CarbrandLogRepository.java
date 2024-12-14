package DealerShop.repository;

import DealerShop.model.CarbrandLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarbrandLogRepository extends JpaRepository<CarbrandLog, Long> {
    @Query("SELECT cl FROM CARBRAND_LOG cl WHERE CONCAT(cl.id, ' ', cl.change_type, ' ', cl.change_date, ' ', cl.old_value, ' ', cl.new_value, ' ', cl.user.email, ' ', cl.carbrand.id) LIKE %?1%")
    List<CarbrandLog> search(String keyword);
}
