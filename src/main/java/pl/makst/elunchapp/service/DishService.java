package pl.makst.elunchapp.service;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DishDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishService {
    List<DishDTO> getAll();
    void put(UUID uuid, DishDTO json);
    void delete(UUID uuid);
    Optional<DishDTO> getByUuid(UUID uuid);
}
