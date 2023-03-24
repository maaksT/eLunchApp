package pl.makst.elunchapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.Order;
import pl.makst.elunchapp.model.OrderItem;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findByUuid(UUID uuid);

}
