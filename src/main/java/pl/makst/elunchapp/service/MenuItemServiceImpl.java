package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.MenuItemDTO;
import pl.makst.elunchapp.repo.DishRepo;
import pl.makst.elunchapp.repo.IngredientRepo;
import pl.makst.elunchapp.repo.MenuItemRepo;
import pl.makst.elunchapp.repo.RestaurantRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepo menuItemRepo;
    private final RestaurantRepo restaurantRepo;
    private final DishRepo dishRepo;
    @Autowired
    public MenuItemServiceImpl(MenuItemRepo menuItemRepo, RestaurantRepo restaurantRepo, DishRepo dishRepo) {
        this.menuItemRepo = menuItemRepo;
        this.restaurantRepo = restaurantRepo;
        this.dishRepo = dishRepo;
    }


    @Override
    public List<MenuItemDTO> getAll() {
        return null;
    }

    @Override
    public void put(UUID uuid, MenuItemDTO menuItemDTO) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<MenuItemDTO> getByUuid(UUID uuid) {
        return Optional.empty();
    }
}
