package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.EmployeeDTO;
import pl.makst.elunchapp.repo.DelivererRepo;
import pl.makst.elunchapp.repo.EmployeeRepo;
import pl.makst.elunchapp.repo.OrderRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return null;
    }

    @Override
    public void put(UUID uuid, EmployeeDTO employeeDTO) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<EmployeeDTO> getByUuid(UUID uuid) {
        return Optional.empty();
    }
}
