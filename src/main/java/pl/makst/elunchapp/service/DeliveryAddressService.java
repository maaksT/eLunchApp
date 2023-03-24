package pl.makst.elunchapp.service;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DeliveryAddressDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryAddressService {
    List<DeliveryAddressDTO> getAll();
    void put(UUID uuid, DeliveryAddressDTO json);
    void delete(UUID uuid);
    Optional<DeliveryAddressDTO> getByUuid(UUID uuid);
}
