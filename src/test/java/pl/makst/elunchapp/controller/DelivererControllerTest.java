package pl.makst.elunchapp.controller;

import com.google.common.truth.Truth8;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import pl.makst.elunchapp.controller.DelivererController;
import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.config.JPAConfiguration;
import pl.makst.elunchapp.model.Deliverer;
import pl.makst.elunchapp.model.enums.Archive;
import pl.makst.elunchapp.model.enums.Sex;
import pl.makst.elunchapp.repo.DelivererRepo;
import pl.makst.elunchapp.repo.OrderRepo;
import pl.makst.elunchapp.service.DelivererService;
import pl.makst.elunchapp.service.DelivererServiceImpl;
import pl.makst.elunchapp.utils.AssertionUtils;
import pl.makst.elunchapp.utils.TestUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        JPAConfiguration.class,
        DelivererControllerTest.Config.class
})
class DelivererControllerTest {
    @Configuration
    public static class Config {
        @Bean
        public DelivererService delivererService(DelivererRepo delivererRepo, OrderRepo orderRepo) {
            return new DelivererServiceImpl(delivererRepo, orderRepo);
        }
        @Bean
        public DelivererController delivererController(DelivererService delivererService) {
            return new DelivererController(delivererService);
        }
    }

    @Autowired
    private DelivererRepo delivererRepo;

    @Autowired
    private DelivererService delivererService;

    @Autowired
    private DelivererController delivererController;

    @Autowired
    private PlatformTransactionManager txManager;

    private static final String STR_UUID = "8faaace7-4d5b-4ade-ba00-c9084995d7ff";

    // add
    @Test
    @Transactional
    public void put1() {
        DelivererDTO delivererJson = TestUtils.delivererDTO(STR_UUID,
                TestUtils.personalDataDTO("John", "Smith", Sex.MALE, "501-501-501", "jh512@gmail.com"),
                TestUtils.logginDataDTO("jSmith", "I@mIronM@n12"), Archive.CURRENT);
        delivererController.put(UUID.fromString(STR_UUID), delivererJson);

        DelivererDTO delivererDB = delivererService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
        AssertionUtils.assertEquals(delivererJson, delivererDB);
    }

    //update
    @Test
    @Transactional
    public void put2() {
        Deliverer deliverer = TestUtils.deliverer(STR_UUID,
                TestUtils.personalData("John", "Smith", Sex.MALE, "501-501-501", "jh512@gmail.com"),
                TestUtils.logginData("jSmith", "I@mIronM@n12"), Archive.CURRENT);
        delivererRepo.save(deliverer);

        DelivererDTO delivererJson = TestUtils.delivererDTO(STR_UUID,
                TestUtils.personalDataDTO("John2", "Smith", Sex.MALE, "501-501-23", "jh31@g2l.com"),
                TestUtils.logginDataDTO("jSmith2", "sadda22"), Archive.ARCHIVE);
        delivererController.put(UUID.fromString(STR_UUID), delivererJson);

        DelivererDTO delivererDB = delivererService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
        AssertionUtils.assertEquals(delivererJson, delivererDB);
    }

    @Test
    @Transactional
    public void delete() {
        TransactionStatus status1 = txManager.getTransaction(TransactionDefinition.withDefaults());
        Deliverer deliverer = TestUtils.deliverer(STR_UUID,
                TestUtils.personalData("John", "Smith", Sex.MALE, "501-501-501", "jh512@gmail.com"),
                TestUtils.logginData("jSmith", "I@mIronM@n12"), Archive.CURRENT);
        delivererRepo.save(deliverer);
        txManager.commit(status1);

        TransactionStatus status2 = txManager.getTransaction(TransactionDefinition.withDefaults());
        delivererController.delete(UUID.fromString(STR_UUID));
        txManager.commit(status2);

        TransactionStatus status3 = txManager.getTransaction(TransactionDefinition.withDefaults());
        Truth8.assertThat(delivererService.getByUuid(UUID.fromString(STR_UUID))).isEmpty();
        txManager.commit(status3);
    }

}


