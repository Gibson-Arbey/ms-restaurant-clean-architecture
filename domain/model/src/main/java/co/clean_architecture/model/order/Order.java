package co.clean_architecture.model.order;

import co.clean_architecture.model.orderdish.OrderDish;
import lombok.Getter;

import java.util.List;

@Getter
public class Order {

    private Long id;

    private final Long restaurantId;

    private final Long customerId;

    private final Long employeeId;

    private final Integer pin;

    private final OrderStatus status;

    private final List<OrderDish> dishes;

    private Order(Long id, Long restaurantId, Long customerId, Long employeeId, Integer pin, OrderStatus status, List<OrderDish> dishes) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.pin = pin;
        this.status = status;
        this.dishes = dishes;
    }

    public static Order create(Long restaurantId, Long customerId, OrderStatus status, List<OrderDish> dishes) {
        return new Order(null, restaurantId, customerId, null, null, status, dishes);
    }

    public static Order restore(Long id, Long restaurantId, Long customerId, Long employeeId, Integer pin, OrderStatus status, List<OrderDish> dishes) {
        return new Order(id, restaurantId, customerId, employeeId, pin, status, dishes);
    }
}
