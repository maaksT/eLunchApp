package pl.makst.elunchapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.Dish;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DishRepo extends JpaRepository<Dish, Long> {
    Optional<Dish> findByUuid(UUID uuid);
}
