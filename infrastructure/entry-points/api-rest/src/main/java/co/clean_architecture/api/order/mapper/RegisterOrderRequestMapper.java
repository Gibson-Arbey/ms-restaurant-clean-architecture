package co.clean_architecture.api.order.mapper;

import co.clean_architecture.api.order.request.RegisterOrderDishRequest;
import co.clean_architecture.api.order.request.RegisterOrderRequest;
import co.clean_architecture.usecase.order.command.RegisterOrderCommand;
import co.clean_architecture.usecase.order.command.RegisterOrderDishCommand;

import java.util.List;

public class RegisterOrderRequestMapper {

    public static RegisterOrderCommand toCommand(RegisterOrderRequest request, Long customerId) {
        if (request == null) return null;

        List<RegisterOrderDishCommand> dishes = request.getDishes()
                .stream()
                .map(RegisterOrderRequestMapper::toDishCommand)
                .toList();

        return new RegisterOrderCommand(
                request.getRestaurantId(),
                customerId,
                dishes
        );
    }

    private static RegisterOrderDishCommand toDishCommand(RegisterOrderDishRequest request) {
        return new RegisterOrderDishCommand(
                request.getDishId(),
                request.getQuantity()
        );
    }
}