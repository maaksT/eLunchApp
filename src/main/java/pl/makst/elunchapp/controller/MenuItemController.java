package pl.makst.elunchapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.DishDTO;
import pl.makst.elunchapp.DTO.IngredientDTO;
import pl.makst.elunchapp.DTO.MenuItemDTO;
import pl.makst.elunchapp.DTO.RestaurantDTO;
import pl.makst.elunchapp.service.IngredientService;
import pl.makst.elunchapp.service.MenuItemService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/menu-items", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuItemController {
    interface MenuItemListListView extends MenuItemDTO.View.Basic, RestaurantDTO.View.Id {}
    interface MenuItemView extends MenuItemDTO.View.Extended, RestaurantDTO.View.Id, DishDTO.View.Id {}
    private final MenuItemService menuItemService;
    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @JsonView(MenuItemListListView.class)
    @GetMapping
    public List<MenuItemDTO> get(){

        return menuItemService.getAll();
    }

    @JsonView(MenuItemView.class)
    @GetMapping("/{uuid}")
    public MenuItemDTO get(@PathVariable UUID uuid){
        return menuItemService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid MenuItemDTO json) {
        menuItemService.put(uuid,json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        menuItemService.delete(uuid);
    }
}
