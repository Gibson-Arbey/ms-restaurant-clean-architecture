package co.clean_architecture.model.orderdish;

import co.clean_architecture.model.exception.InvalidFieldException;
import lombok.Getter;

@Getter
public class OrderDish {

    private Long id;

    private final Long dishId;

    private final Integer quantity;

    private OrderDish(Long id, Long dishId, Integer quantity) {
        if(dishId == null){
            throw new InvalidFieldException("dishId is null");
        }
        if(quantity == null){
            throw new InvalidFieldException("quantity is null");
        }
        this.id = id;
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public static OrderDish create(Long dishId, Integer quantity) {
        return new OrderDish(null, dishId, quantity);
    }

    public static OrderDish restore(Long id, Long dishId, Integer quantity) {
        return new OrderDish(id, dishId, quantity);
    }
}
