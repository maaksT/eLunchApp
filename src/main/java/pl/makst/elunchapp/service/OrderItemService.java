package pl.makst.elunchapp.service;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.DTO.OrderItemDTO;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.OrderItem;
import pl.makst.elunchapp.model.enums.PriceType;

import javax.activation.UnsupportedDataTypeException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemService {
    List<OrderItem> getAll();
    void put(UUID uuid, OrderItem orderItem);
    void delete(UUID uuid);
    Optional<OrderItem> getByUuid(UUID uuid);
    BigDecimal calculatePrice(List<OrderItem> orderItems, BigDecimal startPrice, PriceType priceType) throws UnsupportedDataTypeException;
    BigDecimal applyDiscount(DiscountCode discountCode,BigDecimal orderBruttoPrice) throws UnsupportedDataTypeException;
}
