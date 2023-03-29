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
import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DeliveryAddressDTO;
import pl.makst.elunchapp.config.JPAConfiguration;
import pl.makst.elunchapp.model.Deliverer;
import pl.makst.elunchapp.model.DeliveryAddress;
import pl.makst.elunchapp.model.User;
import pl.makst.elunchapp.model.enums.Archive;
import pl.makst.elunchapp.model.enums.Sex;
import pl.makst.elunchapp.repo.DelivererRepo;
import pl.makst.elunchapp.repo.DeliveryAddressRepo;
import pl.makst.elunchapp.repo.OrderRepo;
import pl.makst.elunchapp.repo.UserRepo;
import pl.makst.elunchapp.service.DelivererService;
import pl.makst.elunchapp.service.DelivererServiceImpl;
import pl.makst.elunchapp.service.DeliveryAddressService;
import pl.makst.elunchapp.service.DeliveryAddressServiceImpl;
import pl.makst.elunchapp.utils.AssertionUtils;
import pl.makst.elunchapp.utils.ConverterUtils;
import pl.makst.elunchapp.utils.TestUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {
        JPAConfiguration.class,
        DeliveryAddressControllerTest.Config.class
})
class DeliveryAddressControllerTest {
    @Configuration
    public static class Config {
        @Bean
        public DeliveryAddressService deliveryAddressService(DeliveryAddressRepo delivererRepo, UserRepo userRepo) {
            return new DeliveryAddressServiceImpl(delivererRepo, userRepo);
        }

        @Bean
        public DeliveryAddressController delivererController(DeliveryAddressService deliveryAddressService) {
            return new DeliveryAddressController(deliveryAddressService);
        }
    }
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DeliveryAddressRepo deliveryAddressRepo;
    @Autowired
    private DeliveryAddressService deliveryAddressService;
    @Autowired
    private DeliveryAddressController deliveryAddressController;
    @Autowired
    private PlatformTransactionManager txManager;
    private static final String STR_UUID = "7f3a7d8c-cd70-11ed-afa1-0242ac120002";

    //add
    @Test
    @Transactional
    public void put1() {
        User user = TestUtils.user("d42e5a62-ce23-11ed-afa1-0242ac120002",
                TestUtils.personalData("Mirek", "Mak", Sex.MALE, "412-312-501", "miras512@ws.com"), null,
                TestUtils.logginData("miras", "I@mIronM@n12"), Archive.CURRENT);
        userRepo.save(user);

        DeliveryAddressDTO deliveryAddressJson = TestUtils.deliveryAddressDTO(STR_UUID,"opis","jana2","12","32b"
                ,"32-131","Warszawa","xxxx","Polska","mazowieckie", ConverterUtils.convert(user));
        deliveryAddressController.put(UUID.fromString(STR_UUID),deliveryAddressJson);

        DeliveryAddressDTO deliveryAddressDB = deliveryAddressService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
        AssertionUtils.assertEquals(deliveryAddressJson, deliveryAddressDB);

    }

    //update
    @Test
    @Transactional
    public void put2() {
        User user = TestUtils.user("d42e5a62-ce23-11ed-afa1-0242ac120002",
                TestUtils.personalData("Mirek", "Mak", Sex.MALE, "412-312-501", "miras512@ws.com"), null,
                TestUtils.logginData("miras", "I@mIronM@n12"), Archive.CURRENT);
        userRepo.save(user);
        DeliveryAddress deliveryAddress = TestUtils.deliveryAddress(STR_UUID,"opis","jana2","12","32b"
                ,"32-131","Warszawa","xxxx","Polska","mazowieckie", user);
        deliveryAddressRepo.save(deliveryAddress);

        DeliveryAddressDTO deliveryAddressJson = TestUtils.deliveryAddressDTO(STR_UUID,"opis3","jana2","122","32b"
                ,"32-131","Warszawa","xxxcx","Polska","mazowieckie", ConverterUtils.convert(user));
        deliveryAddressController.put(UUID.fromString(STR_UUID),deliveryAddressJson);

        DeliveryAddressDTO deliveryAddressDB = deliveryAddressService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
        AssertionUtils.assertEquals(deliveryAddressJson, deliveryAddressDB);
    }

    @Test
    @Transactional
    public void delete() {
        TransactionStatus status1 = txManager.getTransaction(TransactionDefinition.withDefaults());
        User user = TestUtils.user("9986208e-961a-48d4-bf7a-112c627779c2",
                TestUtils.personalData("John", "Smith", Sex.MALE, "501-501-501", "jh512@gmail.com"), null,
                TestUtils.logginData("jSmith", "I@mIronM@n12"), Archive.CURRENT);
        userRepo.save(user);
        DeliveryAddress deliveryAddress = TestUtils.deliveryAddress(STR_UUID, "My address", "Street",
                "51", "", "00-000", "Warsaw", null, "Polans", null, user);
        deliveryAddressRepo.save(deliveryAddress);
        txManager.commit(status1);

        TransactionStatus status2 = txManager.getTransaction(TransactionDefinition.withDefaults());
        deliveryAddressController.delete(UUID.fromString(STR_UUID));
        txManager.commit(status2);

        TransactionStatus status3 = txManager.getTransaction(TransactionDefinition.withDefaults());
        Truth8.assertThat(deliveryAddressService.getByUuid(UUID.fromString(STR_UUID))).isEmpty();
        txManager.commit(status3);
    }
}