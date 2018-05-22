package gridu.jsobyra.market.model.database;

import gridu.jsobyra.market.model.database.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
