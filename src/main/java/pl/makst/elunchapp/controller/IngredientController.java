package pl.makst.elunchapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.EmployeeDTO;
import pl.makst.elunchapp.DTO.IngredientDTO;
import pl.makst.elunchapp.service.EmployeeService;
import pl.makst.elunchapp.service.IngredientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController {
    interface IngredientListListView extends IngredientDTO.View.Basic {}
    interface IngredientView extends IngredientListListView {}
    private final IngredientService ingredientService;
    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @JsonView(IngredientListListView.class)
    @GetMapping
    public List<IngredientDTO> get(){

        return ingredientService.getAll();
    }

    @JsonView(IngredientView.class)
    @GetMapping("/{uuid}")
    public IngredientDTO get(@PathVariable UUID uuid){

        return ingredientService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid IngredientDTO json) {
        ingredientService.put(uuid,json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        ingredientService.delete(uuid);
    }
}
