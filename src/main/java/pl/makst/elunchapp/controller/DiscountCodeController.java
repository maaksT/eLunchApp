package pl.makst.elunchapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DiscountCodeDTO;
import pl.makst.elunchapp.DTO.PeriodDTO;
import pl.makst.elunchapp.service.DelivererService;
import pl.makst.elunchapp.service.DiscountCodeService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/discount-codes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiscountCodeController {
    private final DiscountCodeService discountCodeService;
    interface DiscountCodeListListView extends DiscountCodeDTO.View.Basic, PeriodDTO.View.Basic {}
    interface DiscountCodeView extends DiscountCodeDTO.View.Extended, PeriodDTO.View.Basic {}
    @Autowired
    public DiscountCodeController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @JsonView(DiscountCodeListListView.class)
    @GetMapping
    public List<DiscountCodeDTO> get(){

        return discountCodeService.getAll();
    }

    @JsonView(DiscountCodeView.class)
    @GetMapping("/{uuid}")
    public DiscountCodeDTO get(@PathVariable UUID uuid){
        return discountCodeService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid DiscountCodeDTO json) {
        discountCodeService.put(uuid,json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        discountCodeService.delete(uuid);
    }
}
