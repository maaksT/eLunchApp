package pl.makst.elunchapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.OpenTime;
import pl.makst.elunchapp.model.OperationEvidence;
import pl.makst.elunchapp.model.User;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperationEvidenceRepo extends JpaRepository<OperationEvidence, Long> {

    @Query("SELECT COALESCE(SUM(" +
            "CASE " +
            "WHEN e.type = pl.makst.elunchapp.model.enums.EvidenceType.DEPOSIT THEN e.amount " +
            "WHEN e.type = pl.makst.elunchapp.model.enums.EvidenceType.WITHDRAW " +
            "or e.type = pl.makst.elunchapp.model.enums.EvidenceType.PAYMENT THEN -e.amount " +
            "ELSE 0 " +
            "END" +
            "), 0) from OperationEvidence e where e.user = :user")
    BigDecimal getUserAccountBalance(@Param("user") User user);
}
