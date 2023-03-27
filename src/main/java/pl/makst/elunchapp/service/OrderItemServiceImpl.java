package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.model.DiscountCode;
import pl.makst.elunchapp.model.OrderItem;
import pl.makst.elunchapp.model.enums.PriceType;
import pl.makst.elunchapp.repo.MenuItemRepo;
import pl.makst.elunchapp.repo.OperationEvidenceRepo;
import pl.makst.elunchapp.repo.OrderItemRepo;

import javax.activation.UnsupportedDataTypeException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepo orderItemRepo;
    private final MenuItemRepo menuItemRepo;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepo orderItemRepo, MenuItemRepo menuItemRepo) {
        this.orderItemRepo = orderItemRepo;
        this.menuItemRepo = menuItemRepo;
    }


    @Override
    public List<OrderItem> getAll() {
        return orderItemRepo.findAll();
    }

    @Override
    public void add (OrderItem orderItem) {
        orderItemRepo.save(orderItem);
    }

    @Override
    public void delete(OrderItem orderItem) {
        orderItemRepo.delete(orderItem);
    }

    @Override
    public Optional<OrderItem> getByUuid(UUID uuid) {
        return orderItemRepo.findByUuid(uuid);
    }

    @Override
    public BigDecimal calculatePrice(List<OrderItem> orderItems, BigDecimal startPrice, PriceType priceType) throws UnsupportedDataTypeException {
        BigDecimal orderPrice = startPrice;
        for (OrderItem orderItem : orderItems) {
            switch (priceType) {
                case NETTO:
                    orderPrice = orderPrice.add(
                      orderItem.getMenuItem().getNettoPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                    );
                    break;
                case BRUTTO:
                    orderPrice = orderPrice.add(
                            orderItem.getMenuItem().getBruttoPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                    );
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        return orderPrice;
    }

    @Override
    public BigDecimal applyDiscount(DiscountCode discountCode, BigDecimal orderBruttoPrice) throws UnsupportedDataTypeException {
        if (discountCode == null) {
            return orderBruttoPrice;
        }
        BigDecimal amountToPayBrutto;

        switch (discountCode.getDiscountUnit()) {
            case PLN:
                amountToPayBrutto = orderBruttoPrice.subtract(discountCode.getDiscount());
                break;
            case PERCENT:
                amountToPayBrutto = orderBruttoPrice.multiply(
                        BigDecimal.valueOf(100).subtract(discountCode.getDiscount())
                ).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return amountToPayBrutto;
    }
}
