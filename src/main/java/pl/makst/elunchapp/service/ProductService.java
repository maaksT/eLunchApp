package pl.makst.elunchapp.service;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.ProductDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    List<ProductDTO> getAll();
    void put(UUID uuid, ProductDTO productDTO);
    void delete(UUID uuid);
    Optional<ProductDTO> getByUuid(UUID uuid);
}
