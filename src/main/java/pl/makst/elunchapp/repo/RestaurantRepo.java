package pl.makst.elunchapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.Product;
import pl.makst.elunchapp.model.Restaurant;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByUuid(UUID uuid);
}
