package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.repo.DelivererRepo;
import pl.makst.elunchapp.repo.OrderRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DelivererServiceImpl implements DelivererService {
    private final DelivererRepo delivererRepo;
    private final OrderRepo orderRepo;
    @Autowired
    public DelivererServiceImpl(DelivererRepo delivererRepo, OrderRepo orderRepo) {
        this.delivererRepo = delivererRepo;
        this.orderRepo = orderRepo;
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
