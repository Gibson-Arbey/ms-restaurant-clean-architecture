package co.clean_architecture.usecase.order;

import co.clean_architecture.model.exception.InvalidFieldException;
import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.OrderStatus;
import co.clean_architecture.model.order.exception.OrderNotValidException;
import co.clean_architecture.model.order.gateways.OrderRepository;
import co.clean_architecture.model.orderdish.OrderDish;
import co.clean_architecture.usecase.order.command.RegisterOrderCommand;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RegisterOrderUseCase {

    private final OrderRepository orderRepository;

    public Order execute(RegisterOrderCommand command) {
        if(orderRepository.existsByCustomerIdAndStatusIn(command.customerId(),
                List.of(OrderStatus.IN_PREPARATION, OrderStatus.DELIVERED, OrderStatus.READY))) {
            throw new OrderNotValidException("Customer has an active order");
        }
        validate(command);

        List<OrderDish> dishes = command.dishes().stream()
                .map(d -> OrderDish.create(
                        d.dishId(),
                        d.quantity()
                ))
                .toList();

        Order order = Order.create(
                command.restaurantId(),
                command.customerId(),
                OrderStatus.PENDING,
                dishes
        );

        return orderRepository.save(order);
    }

    private void validate(RegisterOrderCommand command) {

        if (command.restaurantId() == null) {
            throw new InvalidFieldException("restaurantId is required");
        }

        if (command.customerId() == null) {
            throw new InvalidFieldException("customerId is required");
        }

        if (command.dishes() == null || command.dishes().isEmpty()) {
            throw new InvalidFieldException("Order must contain at least one dish");
        }
    }
}