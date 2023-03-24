package pl.makst.elunchapp.service;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.RestaurantDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantService {
    List<RestaurantDTO> getAll();
    void put(UUID uuid, RestaurantDTO restaurantDTO);
    void delete(UUID uuid);
    Optional<RestaurantDTO> getByUuid(UUID uuid);
}
