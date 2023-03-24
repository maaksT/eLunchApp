package pl.makst.elunchapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.makst.elunchapp.model.DeliveryAddress;
import pl.makst.elunchapp.model.DiscountCode;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiscountCodeRepo extends JpaRepository<DiscountCode, Long> {
    Optional<DiscountCode> findByUuid(UUID uuid);
}
