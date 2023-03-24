package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DiscountCodeDTO;
import pl.makst.elunchapp.repo.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {
    private final DiscountCodeRepo discountCodeRepo;
    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;

    @Autowired

    public DiscountCodeServiceImpl(DiscountCodeRepo discountCodeRepo, UserRepo userRepo, RestaurantRepo restaurantRepo) {
        this.discountCodeRepo = discountCodeRepo;
        this.userRepo = userRepo;
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public List<DiscountCodeDTO> getAll() {
        return null;
    }

    @Override
    public void put(UUID uuid, DiscountCodeDTO json) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<DiscountCodeDTO> getByUuid(UUID uuid) {
        return Optional.empty();
    }
}
