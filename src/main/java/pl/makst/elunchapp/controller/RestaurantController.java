package pl.makst.elunchapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.CompanyDataDTO;
import pl.makst.elunchapp.DTO.LogginDataDTO;
import pl.makst.elunchapp.DTO.OpenTimeDTO;
import pl.makst.elunchapp.DTO.RestaurantDTO;
import pl.makst.elunchapp.service.RestaurantService;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;
import java.util.UUID;
@Validated
@RestController
@RequestMapping(path = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    interface RestaurantListListView extends RestaurantDTO.View.Basic {}
    interface RestaurantView extends RestaurantDTO.View.Extended, LogginDataDTO.View.Basic, CompanyDataDTO.View.Extended, OpenTimeDTO.View.Extended {}
    interface DataUpdateValidation extends Default, RestaurantDTO.DataUpdateValidation {}

    private final RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantDTO> get(){

        return restaurantService.getAll();
    }

    @GetMapping("/{uuid}")
    public RestaurantDTO get(@PathVariable UUID uuid){
        return restaurantService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Validated(DataUpdateValidation.class)
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid RestaurantDTO json) {
        restaurantService.put(uuid,json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        restaurantService.delete(uuid);
    }
}
