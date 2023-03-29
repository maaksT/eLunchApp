package pl.makst.elunchapp.utils;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.DelivererDTOBuilder;
import pl.makst.elunchapp.DTO.DeliveryAddressDTO;
import pl.makst.elunchapp.DTO.DeliveryAddressDTOBuilder;
import pl.makst.elunchapp.DTO.DiscountCodeDTO;
import pl.makst.elunchapp.DTO.DiscountCodeDTOBuilder;
import pl.makst.elunchapp.DTO.LogginDataDTO;
import pl.makst.elunchapp.DTO.LogginDataDTOBuilder;
import pl.makst.elunchapp.DTO.OperationEvidenceDTO;
import pl.makst.elunchapp.DTO.OperationEvidenceDTOBuilder;
import pl.makst.elunchapp.DTO.OrderDTO;
import pl.makst.elunchapp.DTO.OrderDTOBuilder;
import pl.makst.elunchapp.DTO.OrderItemDTO;
import pl.makst.elunchapp.DTO.OrderStatusDTO;
import pl.makst.elunchapp.DTO.OrderStatusDTOBuilder;
import pl.makst.elunchapp.DTO.PeriodDTO;
import pl.makst.elunchapp.DTO.PeriodDTOBuilder;
import pl.makst.elunchapp.DTO.PersonalDataDTO;
import pl.makst.elunchapp.DTO.PersonalDataDTOBuilder;
import pl.makst.elunchapp.DTO.RestaurantDTO;
import pl.makst.elunchapp.DTO.UserDTO;
import pl.makst.elunchapp.DTO.UserDTOBuilder;
import pl.makst.elunchapp.model.Address;
import pl.makst.elunchapp.model.AddressBuilder;
import pl.makst.elunchapp.model.CompanyData;
import pl.makst.elunchapp.model.CompanyDataBuilder;
import pl.makst.elunchapp.model.Deliverer;
import pl.makst.elunchapp.model.DelivererBuilder;
import pl.makst.elunchapp.model.DeliveryAddress;
import pl.makst.elunchapp.model.DeliveryAddressBuilder;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.DiscountCodeBuilder;
import pl.makst.elunchapp.model.Dish;
import pl.makst.elunchapp.model.DishBuilder;
import pl.makst.elunchapp.model.Ingredient;
import pl.makst.elunchapp.model.IngredientBuilder;
import pl.makst.elunchapp.model.LogginData;
import pl.makst.elunchapp.model.LogginDataBuilder;
import pl.makst.elunchapp.model.MenuItem;
import pl.makst.elunchapp.model.MenuItemBuilder;
import pl.makst.elunchapp.model.OperationEvidence;
import pl.makst.elunchapp.model.OperationEvidenceBuilder;
import pl.makst.elunchapp.model.Order;
import pl.makst.elunchapp.model.OrderBuilder;
import pl.makst.elunchapp.model.OrderItem;
import pl.makst.elunchapp.model.OrderItemBuilder;
import pl.makst.elunchapp.model.OrderStatus;
import pl.makst.elunchapp.model.OrderStatusBuilder;
import pl.makst.elunchapp.model.Period;
import pl.makst.elunchapp.model.PeriodBuilder;
import pl.makst.elunchapp.model.PersonalData;
import pl.makst.elunchapp.model.PersonalDataBuilder;
import pl.makst.elunchapp.model.Product;
import pl.makst.elunchapp.model.ProductBuilder;
import pl.makst.elunchapp.model.Restaurant;
import pl.makst.elunchapp.model.RestaurantBuilder;
import pl.makst.elunchapp.model.User;
import pl.makst.elunchapp.model.UserBuilder;
import pl.makst.elunchapp.model.enums.Archive;
import pl.makst.elunchapp.model.enums.DiscountUnit;
import pl.makst.elunchapp.model.enums.EvidenceType;
import pl.makst.elunchapp.model.enums.Sex;
import pl.makst.elunchapp.model.enums.VatTax;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static DelivererDTO delivererDTO(String uuid, PersonalDataDTO personalDataDTO, LogginDataDTO logginDataDTO, Archive archive) {
        return new DelivererDTOBuilder()
                .withUuid(UUID.fromString(uuid))
                .withPersonalData(personalDataDTO)
                .withLogginData(logginDataDTO)
                .withArchive(archive)
                .withOrders(new ArrayList<>())
                .build();
    }

    public static PersonalDataDTO personalDataDTO(String name, String surname, Sex sex, String phone, String email) {
        return new PersonalDataDTOBuilder()
                .withName(name)
                .withSurname(surname)
                .withSex(sex)
                .withPhone(phone)
                .withEmail(email)
                .build();
    }

    public static LogginDataDTO logginDataDTO(String login, String passwrd) {
        return new LogginDataDTOBuilder()
                .withLogin(login)
                .withPassword(passwrd)
                .build();
    }

    public static DeliveryAddressDTO deliveryAddressDTO(String uuid, String description, String street, String streetNumber,
                                                        String locaNumber, String postcode, String city, String borough,
                                                        String country, String state, UserDTO userDTO) {
        return new DeliveryAddressDTOBuilder()
                .withUuid(UUID.fromString(uuid))
                .withDescription(description)
                .withStreet(street)
                .withStreetNumber(streetNumber)
                .withLocalNumber(locaNumber)
                .withPostcode(postcode)
                .withCity(city)
                .withBorough(borough)
                .withCounty(country)
                .withState(state)
                .withUser(userDTO)
                .build();
    }

    public static UserDTO userDTO(String uuid, PersonalDataDTO personalDataDTO, List<DeliveryAddressDTO> addresses, LogginDataDTO logginDataDTO, Archive archive) {
        return new UserDTOBuilder()
                .withUuid(UUID.fromString(uuid))
                .withPersonalData(personalDataDTO)
                .withDeliveryAddress(addresses)
                .withLogginData(logginDataDTO)
                .withOperationEvidence(new ArrayList<>())
                .withArchive(archive)
                .build();
    }

    public static DiscountCodeDTO discountCodeDTO(String uuid, String code, BigDecimal discount, DiscountUnit unit, String begin,
                                                  String end, List<UserDTO>  userDTOS, List<RestaurantDTO> restaurantDTOS) {
        return new DiscountCodeDTOBuilder()
                .withUuid(UUID.fromString(uuid))
                .withCode(code)
                .withDiscount(discount)
                .withDiscountUnit(unit)
                .withPeriod(periodDTO(begin, end))
                .withUsers(userDTOS)
                .withRestaurants(restaurantDTOS)
                .build();
    }

    public static DiscountCode discountCode(String uuid, String code, BigDecimal discount, DiscountUnit unit, String begin,
                                            String end, List<User> user, List<Restaurant> restaurant) {
        return new DiscountCodeBuilder()
                .withUuid(UUID.fromString(uuid))
                .withCode(code)
                .withDiscount(discount)
                .withDiscountUnit(unit)
                .withPeriod(period(begin, end))
                .withUsers(user)
                .withRestaurants(restaurant)
                .build();
    }

    public static OperationEvidenceDTO operationEvidenceDTO(String dateInstant, EvidenceType evidenceType, BigDecimal amount, UserDTO userDTO) {
        return new OperationEvidenceDTOBuilder()
                .withDate(Instant.parse(dateInstant))
                .withType(evidenceType)
                .withAmount(amount)
                .withUser(userDTO)
                .build();
    }

    public static PeriodDTO periodDTO(String begin, String end) {
        return new PeriodDTOBuilder()
                .withBegin(begin != null ? LocalDateTime.parse(begin) : null)
                .withEnd(end != null ? LocalDateTime.parse(end) : null)
                .build();
    }

    public static Period period(String begin, String end) {
        return new PeriodBuilder()
                .withBegin(begin != null ? LocalDateTime.parse(begin) : null)
                .withEnd(end != null ? LocalDateTime.parse(end) : null)
                .build();
    }

    public static User user(String uuid, PersonalData personalData, List<DeliveryAddress> addresses, LogginData logginData, Archive archive) {
        return new UserBuilder()
                .withUuid(UUID.fromString(uuid))
                .withPersonalData(personalData)
                .withDeliveryAddress(addresses)
                .withLogginData(logginData)
                .withOperationEvidence(new ArrayList<>())
                .withArchive(archive)
                .build();
    }

    public static OperationEvidence operationEvidence(String dateInstant, EvidenceType evidenceType, BigDecimal amount, User user) {
        return new OperationEvidenceBuilder()
                .withDate(Instant.parse(dateInstant))
                .withType(evidenceType)
                .withAmount(amount)
                .withUser(user)
                .build();
    }

    public static Deliverer deliverer(String uuid, PersonalData personalData, LogginData logginData, Archive archive) {
        return new DelivererBuilder()
                .withUuid(UUID.fromString(uuid))
                .withPersonalData(personalData)
                .withLogginData(logginData)
                .withArchive(archive)
                .withOrders(new ArrayList<>())
                .build();
    }

    public static PersonalData personalData(String name, String surname, Sex sex, String phone, String email) {
        return new PersonalDataBuilder()
                .withName(name)
                .withSurname(surname)
                .withSex(sex)
                .withPhone(phone)
                .withEmail(email)
                .build();
    }

    public static LogginData logginData(String login, String passwrd) {
        return new LogginDataBuilder()
                .withLogin(login)
                .withPassword(passwrd)
                .build();
    }

    public static DeliveryAddress deliveryAddress(String uuid, String description, String street, String streetNumber,
                                                  String locaNumber, String postcode, String city, String borough,
                                                  String country, String state, User user) {
        return new DeliveryAddressBuilder()
                .withUuid(UUID.fromString(uuid))
                .withDescription(description)
                .withStreet(street)
                .withStreetNumber(streetNumber)
                .withLocalNumber(locaNumber)
                .withPostcode(postcode)
                .withCity(city)
                .withBorough(borough)
                .withCounty(country)
                .withState(state)
                .withUser(user)
                .build();
    }

    public static Restaurant restaurant(String uuid, String name, LogginData logginData, CompanyData companyData, Archive archive) {
        return new RestaurantBuilder()
                .withUuid(UUID.fromString(uuid))
                .withName(name)
                .withLogginData(logginData)
                .withCompanyData(companyData)
                .withArchive(archive)
                .withOrders(new ArrayList<>())
                .withOpenTimes(new ArrayList<>())
                .withMenu(new ArrayList<>())
                .withDiscountCodes(new ArrayList<>())
                .build();
    }

    public static CompanyData companyData(String name, Address address, String NIP, String REGON, String phone, String email) {
        return new CompanyDataBuilder()
                .withName(name)
                .withAddress(address)
                .withNIP(NIP)
                .withREGON(REGON)
                .withPhone(phone)
                .withEmail(email)
                .build();
    }

    public static Address address(String street, String streetNumber, String postcode, String city) {
        return new AddressBuilder()
                .withStreet(street)
                .withStreetNumber(streetNumber)
                .withPostcode(postcode)
                .withCity(city)
                .build();
    }

    public static Order order(String uuid, DiscountCode discountCode, String note, User userDTO,
                              Deliverer deliverer, DeliveryAddress deliveryAddress, Restaurant restaurant, BigDecimal nettoPrice,
                              BigDecimal bruttoPrice, BigDecimal amountToPayBrutto, OrderStatus orderStatus, OrderItem... orderItems) {
        return new OrderBuilder()
                .withUuid(UUID.fromString(uuid))
                .withDiscountCode(discountCode)
                .withNote(note)
                .withUser(userDTO)
                .withDeliverer(deliverer)
                .withDeliveryAddress(deliveryAddress)
                .withRestaurant(restaurant)
                .withOrderItems(Arrays.asList(orderItems))
                .withNettoPrice(nettoPrice)
                .withBruttoPrice(bruttoPrice)
                .withAmountToPayBrutto(amountToPayBrutto)
                .withStatus(orderStatus)
                .build();
    }

    public static OrderStatus orderStatus(@Nullable String orderTimeInstant, Boolean isPaid, @Nullable String giveOutTimeInstant, @Nullable String deliveryTimeInstant) {
        return new OrderStatusBuilder()
                .withOrderTime(orderTimeInstant != null ? Instant.parse(orderTimeInstant) : null)
                .withIsPaid(isPaid)
                .withGiveOutTime(giveOutTimeInstant != null ? Instant.parse(giveOutTimeInstant) : null)
                .withDeliveryTime(deliveryTimeInstant != null ? Instant.parse(deliveryTimeInstant) : null)
                .build();
    }

    public static OrderStatusDTO orderStatusDTO(@Nullable String orderTimeInstant, Boolean isPaid, @Nullable String giveOutTimeInstant, @Nullable String deliveryTimeInstant) {
        return new OrderStatusDTOBuilder()
                .withOrderTime(orderTimeInstant != null ? Instant.parse(orderTimeInstant) : null)
                .withPaid(isPaid)
                .withGiveOutTime(giveOutTimeInstant != null ? Instant.parse(giveOutTimeInstant) : null)
                .withDeliveryTime(deliveryTimeInstant != null ? Instant.parse(deliveryTimeInstant) : null)
                .build();
    }

    public static OrderDTO orderDTO(String uuid, DiscountCodeDTO discountCodeDTO, String note, UserDTO userDTO,
                                    DelivererDTO delivererDTO, DeliveryAddressDTO deliveryAddressDTO, RestaurantDTO restaurantDTO, OrderItemDTO... orderItemDTOS) {
        return new OrderDTOBuilder()
                .withUuid(UUID.fromString(uuid))
                .withDiscountCode(discountCodeDTO)
                .withNote(note)
                .withUser(userDTO)
                .withDeliverer(delivererDTO)
                .withDeliveryAddressDTO(deliveryAddressDTO)
                .withRestaurant(restaurantDTO)
                .withOrderItems(Arrays.asList(orderItemDTOS))
                .build();
    }

    public static OrderItem orderItem(String uuid, Integer quantity, MenuItem menuItem) {
        return new OrderItemBuilder()
                .withUuid(UUID.fromString(uuid))
                .withQuantity(quantity)
                .withMenuItem(menuItem)
                .build();
    }

    public static MenuItem menuItem(String uuid, String name, BigDecimal nettoPrice, VatTax vatTax, BigDecimal bruttoPrice, Restaurant restaurant, Dish... dishes) {
        return new MenuItemBuilder()
                .withUuid(UUID.fromString(uuid))
                .withName(name)
                .withNettoPrice(nettoPrice)
                .withVatTax(vatTax)
                .withBruttoPrice(bruttoPrice)
                .withRestaurant(restaurant)
                .withDishes(Arrays.asList(dishes))
                .build();
    }

    public static Dish dish(String uuid, Integer quantity, Product product) {
        return new DishBuilder()
                .withUuid(UUID.fromString(uuid))
                .withQuantity(quantity)
                .withProduct(product)
                .build();
    }

    public static Product product(String uuid, String name, Ingredient... ingredients) {
        return new ProductBuilder()
                .withUuid(UUID.fromString(uuid))
                .withName(name)
                .withIngredients(Arrays.asList(ingredients))
                .build();
    }

    public static Ingredient ingredient(String uuid, String name, Boolean isAllergen) {
        return new IngredientBuilder()
                .withUuid(UUID.fromString(uuid))
                .withName(name)
                .withAllergen(isAllergen)
                .build();
    }
}