package pl.makst.elunchapp.service;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.OpenTimeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OpenTimeService {
    List<OpenTimeDTO> getAll();
    void put(UUID uuid, OpenTimeDTO openTimeDTO);
    void delete(UUID uuid);
    Optional<OpenTimeDTO> getByUuid(UUID uuid);
}
