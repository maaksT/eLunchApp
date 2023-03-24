package pl.makst.elunchapp.service;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAll();
    void put(UUID uuid, UserDTO userDTO);
    void delete(UUID uuid);
    Optional<UserDTO> getByUuid(UUID uuid);
    void validateNewOperation(UUID uuid, UserDTO userDTO);
}
