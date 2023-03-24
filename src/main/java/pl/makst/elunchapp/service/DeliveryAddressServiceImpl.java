package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.repo.DelivererRepo;
import pl.makst.elunchapp.repo.DeliveryAddressRepo;
import pl.makst.elunchapp.repo.OrderRepo;
import pl.makst.elunchapp.repo.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private final DeliveryAddressRepo deliveryAddressRepo;
    private final UserRepo userRepo;
    @Autowired
    public DeliveryAddressServiceImpl(DeliveryAddressRepo deliveryAddressRepo, UserRepo userRepo) {
        this.deliveryAddressRepo = deliveryAddressRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<DelivererDTO> getAll() {
        return null;
    }

    @Override
    public void put(UUID uuid, DelivererDTO delivererDTO) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<DelivererDTO> getByUuid(UUID uuid) {
        return Optional.empty();
    }
}
