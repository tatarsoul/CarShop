package DealerShop.repository;

import DealerShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM USERS u WHERE u.login = :login")
    Optional<User> findByLogin(@Param("login") String login);

    @Query("SELECT u FROM USERS u WHERE concat(u.id, ' ', u.email, ' ', u.login, ' ',u.name, ' ', u.pass, ' ', u.role.name) LIKE %?1%")
    List<User> search(String keyword);
}
