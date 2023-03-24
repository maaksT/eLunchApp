package pl.makst.elunchapp.service;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.OperationEvidenceDTO;
import pl.makst.elunchapp.model.OperationEvidence;
import pl.makst.elunchapp.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OperationEvidenceService {
    List<OperationEvidence> getAll();
    void put(UUID uuid, OperationEvidence operationEvidence);
    void delete(UUID uuid);
    Optional<OperationEvidence> getByUuid(UUID uuid);
    BigDecimal getUserAccountBalance(User user);
    BigDecimal getAccountBalanceAfterOperation(OperationEvidence operationEvidence);
}
