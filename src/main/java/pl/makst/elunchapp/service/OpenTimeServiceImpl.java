package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.OpenTimeDTO;
import pl.makst.elunchapp.repo.DishRepo;
import pl.makst.elunchapp.repo.MenuItemRepo;
import pl.makst.elunchapp.repo.OpenTimeRepo;
import pl.makst.elunchapp.repo.RestaurantRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OpenTimeServiceImpl implements OpenTimeService {
    private final OpenTimeRepo openTimeRepo;
    private final RestaurantRepo restaurantRepo;
    @Autowired
    public OpenTimeServiceImpl(OpenTimeRepo openTimeRepo, RestaurantRepo restaurantRepo) {
        this.openTimeRepo = openTimeRepo;
        this.restaurantRepo = restaurantRepo;
    }


    @Override
    public List<OpenTimeDTO> getAll() {
        return null;
    }

    @Override
    public void put(UUID uuid, OpenTimeDTO openTimeDTO) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<OpenTimeDTO> getByUuid(UUID uuid) {
        return Optional.empty();
    }
}
