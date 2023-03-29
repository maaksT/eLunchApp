package pl.makst.elunchapp.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.makst.elunchapp.DTO.UserDTO;
import pl.makst.elunchapp.config.JPAConfiguration;
import pl.makst.elunchapp.events.OperationEvidenceCreator;
import pl.makst.elunchapp.model.OperationEvidence;
import pl.makst.elunchapp.model.User;
import pl.makst.elunchapp.repo.UserRepo;
import pl.makst.elunchapp.service.OperationEvidenceService;
import pl.makst.elunchapp.utils.ConverterUtils;

import java.math.BigDecimal;

@Component
public class OperationEvidenceListener {
    private final OperationEvidenceService operationEvidenceService;
    private final UserRepo userRepo;

    @Autowired
    public OperationEvidenceListener(OperationEvidenceService operationEvidenceService, UserRepo userRepo) {
        this.operationEvidenceService = operationEvidenceService;
        this.userRepo = userRepo;
    }

    @EventListener
    public void onAddOperation(OperationEvidenceCreator operationEvidenceCreator) {
        UserDTO userDTO = operationEvidenceCreator.getUserDTO();
        OperationEvidence operationEvidence = ConverterUtils.convert(userDTO.getOperationEvidence().stream().findFirst().orElseThrow());
        User user = userRepo.findByUuid(userDTO.getUuid()).orElseThrow();
        operationEvidence.setUser(user);
        validateAccountBalanceAfterOperation(operationEvidence);
        operationEvidenceService.add(operationEvidence);

    }

    private void validateAccountBalanceAfterOperation(OperationEvidence operationEvidence) {
        BigDecimal accountBalanceAfterOperation = operationEvidenceService.getAccountBalanceAfterOperation(operationEvidence);
        if (accountBalanceAfterOperation.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
