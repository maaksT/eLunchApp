package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.IngredientDTO;
import pl.makst.elunchapp.repo.EmployeeRepo;
import pl.makst.elunchapp.repo.IngredientRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepo ingredientRepo;
    @Autowired
    public IngredientServiceImpl(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public List<IngredientDTO> getAll() {
        return null;
    }

    @Override
    public void put(UUID uuid, IngredientDTO ingredientDTO) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<IngredientDTO> getByUuid(UUID uuid) {
        return Optional.empty();
    }
}
