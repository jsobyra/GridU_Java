package gridu.jsobyra.market.model.database;

import gridu.jsobyra.market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}