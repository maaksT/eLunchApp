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
        return null;
    }

    @Override
    public void put(UUID uuid, OrderItem orderItem) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<OrderItem> getByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public BigDecimal calculatePrice(List<OrderItem> orderItems, BigDecimal startPrice, PriceType priceType) throws UnsupportedDataTypeException {
        return null;
    }

    @Override
    public BigDecimal applyDiscount(DiscountCode discountCode, BigDecimal orderBruttoPrice) throws UnsupportedDataTypeException {
        return null;
    }
}
