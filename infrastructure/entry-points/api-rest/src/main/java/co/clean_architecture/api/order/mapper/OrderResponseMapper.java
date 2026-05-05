package co.clean_architecture.api.order.mapper;

import co.clean_architecture.api.order.response.OrderDishResponse;
import co.clean_architecture.api.order.response.OrderResponse;
import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.orderdish.OrderDish;

import java.util.List;

public class OrderResponseMapper {

    public static OrderResponse toResponse(Order order) {
        if (order == null) return null;

        return OrderResponse.builder()
                .id(order.getId())
                .restaurantId(order.getRestaurantId())
                .customerId(order.getCustomerId())
                .employeeId(order.getEmployeeId())
                .pin(order.getPin())
                .status(order.getStatus())
                .dishes(toDishResponseList(order.getDishes()))
                .build();
    }

    private static List<OrderDishResponse> toDishResponseList(List<OrderDish> dishes) {
        if (dishes == null) return List.of();

        return dishes.stream()
                .map(OrderResponseMapper::toDishResponse)
                .toList();
    }

    private static OrderDishResponse toDishResponse(OrderDish dish) {
        return OrderDishResponse.builder()
                .dishId(dish.getDishId())
                .quantity(dish.getQuantity())
                .build();
    }
}