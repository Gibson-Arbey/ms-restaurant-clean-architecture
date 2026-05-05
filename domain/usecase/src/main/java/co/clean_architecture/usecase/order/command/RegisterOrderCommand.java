package co.clean_architecture.usecase.order.command;

import java.util.List;

public record RegisterOrderCommand(Long restaurantId, Long customerId, List<RegisterOrderDishCommand> dishes) {
}
