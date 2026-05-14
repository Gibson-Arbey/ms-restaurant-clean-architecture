package co.clean_architecture.model.order;

import co.clean_architecture.model.exception.InvalidFieldException;
import co.clean_architecture.model.order.exception.InvalidOrderStatusException;
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

    public static Order assignEmployee(Order order, Long employeeId) {
        if (order == null) {
            throw new InvalidFieldException("Order cannot be null");
        }

        if (employeeId == null) {
            throw new InvalidFieldException("EmployeeId cannot be null");
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new InvalidOrderStatusException("Order is not pending");
        }

        return new Order(
                order.getId(),
                order.getRestaurantId(),
                order.getCustomerId(),
                employeeId,
                order.getPin(),
                OrderStatus.IN_PREPARATION,
                order.getDishes()
        );
    }

    public static Order markOrdenAsReady(Order order, Integer pin) {
        if (order == null) {
            throw new InvalidFieldException("Order cannot be null");
        }

        if (order.getStatus() != OrderStatus.IN_PREPARATION) {
            throw new InvalidOrderStatusException("Order is not pending");
        }

        if(pin == null) {
            throw new InvalidOrderStatusException("Pin cannot be null");
        }
        return new Order(
                order.getId(),
                order.getRestaurantId(),
                order.getCustomerId(),
                order.getEmployeeId(),
                pin,
                OrderStatus.READY,
                order.getDishes()
        );
    }

    public static Order markOrderAsCancel(Order order) {
        if (order == null) {
            throw new InvalidFieldException("Order cannot be null");
        }
        if (order.getStatus() != OrderStatus.PENDING) {
           throw new InvalidOrderStatusException("We're sorry, your order is already being processed and cannot be canceled.");
        }
        return new Order(
                order.getId(),
                order.getRestaurantId(),
                order.getCustomerId(),
                order.getEmployeeId(),
                order.getPin(),
                OrderStatus.CANCEL,
                order.getDishes()
        );
    }

    public static Order markOrderAsDelivered(Order order) {
        if (order == null) {
            throw new InvalidFieldException("Order cannot be null");
        }
        if (order.getStatus() != OrderStatus.READY) {
            throw new InvalidOrderStatusException("Order is not ready");
        }
        return new Order(
                order.getId(),
                order.getRestaurantId(),
                order.getCustomerId(),
                order.getEmployeeId(),
                order.getPin(),
                OrderStatus.DELIVERED,
                order.getDishes()
        );
    }
}
