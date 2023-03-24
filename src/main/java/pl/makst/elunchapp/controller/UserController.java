package pl.makst.elunchapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.*;
import pl.makst.elunchapp.service.RestaurantService;
import pl.makst.elunchapp.service.UserService;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;
import java.util.UUID;
@Validated
@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    interface UserListListView extends UserDTO.View.Basic, PersonalDataDTO.View.Basic {}
    interface UserView extends UserDTO.View.Extended, PersonalDataDTO.View.Extended, LogginDataDTO.View.Basic,
            DeliveryAddressDTO.View.Basic, OperationEvidenceDTO.View.Extended, DiscountCodeDTO.View.Extended {}

    interface DataUpdateValidation extends Default, UserDTO.DataUpdateValidation {}
    interface NewOperationValidation extends Default, UserDTO.NewOperationValidation {}

    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UserController(UserService userService, ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping
    public List<UserDTO> get(){
        return userService.getAll();
    }

    @GetMapping("/{uuid}")
    public UserDTO get(@PathVariable UUID uuid){
        return userService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Validated(DataUpdateValidation.class)
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid UserDTO json) {
        userService.put(uuid,json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        userService.delete(uuid);
    }

    @Transactional
    @Validated(NewOperationValidation.class)
    @PostMapping("/{uuid}/new-operation")
    public void postOperation(@PathVariable UUID uuid, @RequestBody @Valid UserDTO json) {
        userService.validateNewOperation(uuid,json);
    }
    @Transactional
    @Validated(NewOperationValidation.class)
    @GetMapping("/{uuid}/delivery-adresses")
    public List<DeliveryAddressDTO> getUserAdresses(@PathVariable UUID uuid){
        UserDTO userDTO = userService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return userDTO.getDeliveryAddress();
    }


}
