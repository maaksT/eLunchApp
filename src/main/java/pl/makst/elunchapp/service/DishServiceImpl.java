package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DishDTO;
import pl.makst.elunchapp.repo.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepo dishRepo;
    private final MenuItemRepo menuItemRepo;
    private final ProductRepo productRepo;
    @Autowired
    public DishServiceImpl(DishRepo dishRepo, MenuItemRepo menuItemRepo, ProductRepo productRepo) {
        this.dishRepo = dishRepo;
        this.menuItemRepo = menuItemRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<DishDTO> getAll() {
        return null;
    }

    @Override
    public void put(UUID uuid, DishDTO json) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<DishDTO> getByUuid(UUID uuid) {
        return Optional.empty();
    }
}
