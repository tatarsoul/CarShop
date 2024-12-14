package DealerShop.repository;

import DealerShop.model.Dealershop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealershopRepository extends JpaRepository<Dealershop, Long> {
    @Query("SELECT d FROM DEALERSHOP d WHERE CONCAT(d.id, ' ', d.name, ' ', d.descr, ' ', d.address, ' ', d.contact) LIKE %?1%")
    List<Dealershop> search(String keyword);
}
