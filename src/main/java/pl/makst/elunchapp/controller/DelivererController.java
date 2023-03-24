package pl.makst.elunchapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.*;
import pl.makst.elunchapp.service.DelivererService;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;
import java.util.UUID;
@Validated
@RestController
@RequestMapping(path = "/api/deliverers", produces = MediaType.APPLICATION_JSON_VALUE)
public class DelivererController {
    interface DelivererListView extends DelivererDTO.View.Basic, PersonalDataDTO.View.Basic {}
    interface DelivererView extends DelivererDTO.View.Extended, PersonalDataDTO.View.Extended, LogginDataDTO.View.Basic, OrderDTO.View.Extended {}
    interface NewDelivererValidation extends Default, DelivererDTO.NewDelivererValidation {}

    private final DelivererService delivererService;

    @Autowired
    public DelivererController(DelivererService delivererService) {
        this.delivererService = delivererService;
    }

    @JsonView(DelivererListView.class)
    @GetMapping
    public List<DelivererDTO> get(){
        return delivererService.getAll();
    }

    @JsonView(DelivererView.class)
    @GetMapping("/{uuid}")
    public DelivererDTO get(@PathVariable UUID uuid){
        return delivererService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Validated(DelivererDTO.NewDelivererValidation.class)
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid DelivererDTO json) {
        delivererService.put(uuid,json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        delivererService.delete(uuid);
    }
}
