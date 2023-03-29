package pl.makst.elunchapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DeliveryAddressDTO;
import pl.makst.elunchapp.DTO.UserDTO;
import pl.makst.elunchapp.service.DelivererService;
import pl.makst.elunchapp.service.DeliveryAddressService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/delivery-address", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeliveryAddressController {
    private final DeliveryAddressService deliveryAddressService;

    interface DeliveryAddressListListView extends DeliveryAddressDTO.View.Basic, UserDTO.View.Id {}
    interface DeliveryAddressView extends DeliveryAddressDTO.View.Extended, UserDTO.View.Id {}
    @Autowired
    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @JsonView(DeliveryAddressListListView.class)
    @GetMapping
    public List<DeliveryAddressDTO> get(){

        return deliveryAddressService.getAll();
    }

    @JsonView(DeliveryAddressView.class)
    @GetMapping("/{uuid}")
    public DeliveryAddressDTO get(@PathVariable UUID uuid){
        return deliveryAddressService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid DeliveryAddressDTO json) {
        deliveryAddressService.put(uuid,json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        deliveryAddressService.delete(uuid);
    }
}
