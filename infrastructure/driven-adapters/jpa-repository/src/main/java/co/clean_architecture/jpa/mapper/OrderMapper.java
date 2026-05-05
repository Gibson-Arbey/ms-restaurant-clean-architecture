package co.clean_architecture.jpa.mapper;

import co.clean_architecture.jpa.entity.OrderEntity;
import co.clean_architecture.jpa.entity.OrderDishEntity;
import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.orderdish.OrderDish;

import java.util.List;

public class OrderMapper {

    public static Order toDomain(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        List<OrderDish> dishes = entity.getDishes() == null
                ? List.of()
                : entity.getDishes()
                  .stream()
                  .map(OrderMapper::toDomainDish)
                  .toList();

        return Order.restore(
                entity.getId(),
                entity.getRestaurantId(),
                entity.getCustomerId(),
                entity.getEmployeeId(),
                entity.getPin(),
                entity.getStatus(),
                dishes
        );
    }

    public static OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }

        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setRestaurantId(order.getRestaurantId());
        entity.setCustomerId(order.getCustomerId());
        entity.setEmployeeId(order.getEmployeeId());
        entity.setPin(order.getPin());
        entity.setStatus(order.getStatus());

        List<OrderDishEntity> dishes = order.getDishes() == null
                ? List.of()
                : order.getDishes()
                  .stream()
                  .map(d -> toEntityDish(d, entity))
                  .toList();

        entity.setDishes(dishes);

        return entity;
    }

    // Mappers for OrderDish
    private static OrderDish toDomainDish(OrderDishEntity entity) {
        return OrderDish.restore(
                entity.getId(),
                entity.getDishId(),
                entity.getQuantity()
        );
    }

    private static OrderDishEntity toEntityDish(OrderDish dish, OrderEntity orderEntity) {
        OrderDishEntity entity = new OrderDishEntity();
        entity.setId(dish.getId());
        entity.setDishId(dish.getDishId());
        entity.setQuantity(dish.getQuantity());
        entity.setOrder(orderEntity);

        return entity;
    }
}