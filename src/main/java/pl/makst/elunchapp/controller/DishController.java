package pl.makst.elunchapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DishDTO;
import pl.makst.elunchapp.DTO.MenuItemDTO;
import pl.makst.elunchapp.DTO.ProductDTO;
import pl.makst.elunchapp.service.DelivererService;
import pl.makst.elunchapp.service.DishService;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;
import java.util.UUID;
@Validated
@RestController
@RequestMapping(path = "/api/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    interface DishListListView extends DishDTO.View.Basic {}
    interface DishView extends DishDTO.View.Extended, ProductDTO.View.Extended, MenuItemDTO.View.Basic {}
    interface DishDataUpdateValidation extends Default, DishDTO.DataUpdateValidation {}

    private final DishService dishService;
    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @JsonView(DishListListView.class)
    @GetMapping
    public List<DishDTO> get(){
        return  dishService.getAll();
    }

    @JsonView(DishView.class)
    @GetMapping("/{uuid}")
    public DishDTO get(@PathVariable UUID uuid){
        return dishService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Validated(DishDataUpdateValidation.class)
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid DishDTO json) {
        dishService.put(uuid,json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        dishService.delete(uuid);
    }
}
