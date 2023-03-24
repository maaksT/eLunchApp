package pl.makst.elunchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.makst.elunchapp.DTO.OrderDTO;
import pl.makst.elunchapp.DTO.OrderStatusDTO;
import pl.makst.elunchapp.DTO.UserDTO;
import pl.makst.elunchapp.repo.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;
    private final DelivererRepo delivererRepo;
    private final MenuItemRepo menuItemRepo;
    private final DiscountCodeRepo discountCodeRepo;
    private final OrderItemRepo orderItemRepo;
    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, UserRepo userRepo, RestaurantRepo restaurantRepo, DelivererRepo delivererRepo, MenuItemRepo menuItemRepo, DiscountCodeRepo discountCodeRepo, OrderItemRepo orderItemRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.restaurantRepo = restaurantRepo;
        this.delivererRepo = delivererRepo;
        this.menuItemRepo = menuItemRepo;
        this.discountCodeRepo = discountCodeRepo;
        this.orderItemRepo = orderItemRepo;
    }

    @Override
    public List<OrderDTO> getAll() {
        return null;
    }

    @Override
    public void put(UUID uuid, OrderDTO orderDTO) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<OrderDTO> getByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void setIsPaid(OrderDTO orderDTO) {

    }

    @Override
    public void setIsGivedOut(UUID uuid, OrderStatusDTO orderStatusDTO) {

    }

    @Override
    public void setIsDelivered(UUID uuid, OrderStatusDTO orderStatusDTO) {

    }

    @Override
    public UserDTO newOperationForPaidOrder(OrderDTO orderDTO) {
        return null;
    }
}
