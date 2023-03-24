package pl.makst.elunchapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.MenuItem;
import pl.makst.elunchapp.model.OpenTime;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OpenTimeRepo extends JpaRepository<OpenTime, Long> {
    Optional<OpenTime> findByUuid(UUID uuid);
}
