package co.clean_architecture.usecase.order.command;

public record RegisterOrderDishCommand(Long dishId, Integer quantity) {
}
