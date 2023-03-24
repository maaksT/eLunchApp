package pl.makst.elunchapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.DishDTO;
import pl.makst.elunchapp.DTO.EmployeeDTO;
import pl.makst.elunchapp.DTO.LogginDataDTO;
import pl.makst.elunchapp.DTO.PersonalDataDTO;
import pl.makst.elunchapp.service.DishService;
import pl.makst.elunchapp.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    interface EmployeeListListView extends EmployeeDTO.View.Basic, PersonalDataDTO.View.Basic {}
    interface EmployeeView extends EmployeeDTO.View.Extended, PersonalDataDTO.View.Extended, LogginDataDTO.View.Basic {}
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @JsonView(EmployeeListListView.class)
    @GetMapping
    public List<EmployeeDTO> get(){

        return employeeService.getAll();
    }

    @JsonView(EmployeeView.class)
    @GetMapping("/{uuid}")
    public EmployeeDTO get(@PathVariable UUID uuid){

        return employeeService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid EmployeeDTO json) {
        employeeService.put(uuid,json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        employeeService.delete(uuid);
    }
}
