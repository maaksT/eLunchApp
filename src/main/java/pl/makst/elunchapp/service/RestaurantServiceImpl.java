package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.RestaurantDTO;
import pl.makst.elunchapp.repo.DishRepo;
import pl.makst.elunchapp.repo.IngredientRepo;
import pl.makst.elunchapp.repo.ProductRepo;
import pl.makst.elunchapp.repo.RestaurantRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }


    @Override
    public List<RestaurantDTO> getAll() {
        return null;
    }

    @Override
    public void put(UUID uuid, RestaurantDTO restaurantDTO) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<RestaurantDTO> getByUuid(UUID uuid) {
        return Optional.empty();
    }
}
