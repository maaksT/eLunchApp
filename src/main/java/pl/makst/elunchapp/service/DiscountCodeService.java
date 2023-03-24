package pl.makst.elunchapp.service;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DiscountCodeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiscountCodeService {
    List<DiscountCodeDTO> getAll();
    void put(UUID uuid, DiscountCodeDTO json);
    void delete(UUID uuid);
    Optional<DiscountCodeDTO> getByUuid(UUID uuid);
}
