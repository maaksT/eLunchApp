package pl.makst.elunchapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.Ingredient;
import pl.makst.elunchapp.model.MenuItem;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByUuid(UUID uuid);
}
