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
import pl.makst.elunchapp.controller.DiscountCodeController;
import pl.makst.elunchapp.DTO.DeliveryAddressDTO;
import pl.makst.elunchapp.DTO.DiscountCodeDTO;
import pl.makst.elunchapp.config.JPAConfiguration;
import pl.makst.elunchapp.model.DeliveryAddress;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.User;
import pl.makst.elunchapp.model.enums.Archive;
import pl.makst.elunchapp.model.enums.DiscountUnit;
import pl.makst.elunchapp.model.enums.Sex;
import pl.makst.elunchapp.repo.DelivererRepo;
import pl.makst.elunchapp.repo.DiscountCodeRepo;
import pl.makst.elunchapp.repo.OrderRepo;
import pl.makst.elunchapp.repo.RestaurantRepo;
import pl.makst.elunchapp.repo.UserRepo;
import pl.makst.elunchapp.service.DelivererService;
import pl.makst.elunchapp.service.DelivererServiceImpl;
import pl.makst.elunchapp.service.DiscountCodeService;
import pl.makst.elunchapp.service.DiscountCodeServiceImpl;
import pl.makst.elunchapp.utils.AssertionUtils;
import pl.makst.elunchapp.utils.ConverterUtils;
import pl.makst.elunchapp.utils.TestUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        JPAConfiguration.class,
        DiscountCodeControllerTest.Config.class
})
class DiscountCodeControllerTest {
    @Configuration
    public static class Config {
        @Bean
        public DiscountCodeService discountCodeService(DiscountCodeRepo discountCodeRepo, UserRepo userRepo, RestaurantRepo restaurantRepo) {
            return new DiscountCodeServiceImpl(discountCodeRepo, userRepo, restaurantRepo);
        }
        @Bean
        public DiscountCodeController discountCodeController(DiscountCodeService discountCodeService) {
            return new DiscountCodeController(discountCodeService);
        }
    }

    @Autowired
    private DiscountCodeRepo discountCodeRepo;

    @Autowired
    private DiscountCodeService discountCodeService;

    @Autowired
    private DiscountCodeController discountCodeController;

    @Autowired
    private PlatformTransactionManager txManager;
        private static final String STR_UUID = "a1417afe-cd7d-11ed-afa1-0242ac120002";

        //add
        @Test
        @Transactional
        public void put1() {
            DiscountCodeDTO discountCodeJson = TestUtils.discountCodeDTO(STR_UUID, "BLACK FRIDAY", new BigDecimal("25.00"), DiscountUnit.PERCENT,
                    "2020-01-01T00:00:00", "2020-02-01T00:00:00", null, null);
            discountCodeController.put(UUID.fromString(STR_UUID), discountCodeJson);

            DiscountCodeDTO discountCodeDB = discountCodeService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
            AssertionUtils.assertEquals(discountCodeJson, discountCodeDB);

        }

        //update
        @Test
        @Transactional
        public void put2() {
            DiscountCode discountCode = TestUtils.discountCode(STR_UUID, "BLACK FRIDAY", new BigDecimal("25.00"), DiscountUnit.PERCENT,
                    "2020-01-01T00:00:00", "2020-02-01T00:00:00", null, null);
            discountCodeRepo.save(discountCode);

            DiscountCodeDTO discountCodeJson = TestUtils.discountCodeDTO(STR_UUID, "BLACK FRIDAY 2", new BigDecimal("20.00"), DiscountUnit.PLN,
                    "2020-05-01T00:00:00", "2020-06-01T00:00:00", null, null);
            discountCodeController.put(UUID.fromString(STR_UUID), discountCodeJson);

            DiscountCodeDTO discountCodeDB = discountCodeService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
            AssertionUtils.assertEquals(discountCodeJson, discountCodeDB);
        }

    @Test
    @Transactional
    public void delete() {
        TransactionStatus status1 = txManager.getTransaction(TransactionDefinition.withDefaults());
        DiscountCode discountCodeJson = TestUtils.discountCode(STR_UUID, "BLACK FRIDAY", new BigDecimal("25.00"), DiscountUnit.PERCENT,
                "2020-01-01T00:00:00", "2020-02-01T00:00:00", null, null);
        discountCodeRepo.save(discountCodeJson);
        txManager.commit(status1);

        TransactionStatus status2 = txManager.getTransaction(TransactionDefinition.withDefaults());
        discountCodeController.delete(UUID.fromString(STR_UUID));
        txManager.commit(status2);

        TransactionStatus status3 = txManager.getTransaction(TransactionDefinition.withDefaults());
        Truth8.assertThat(discountCodeService.getByUuid(UUID.fromString(STR_UUID))).isEmpty();
        txManager.commit(status3);
    }
}
