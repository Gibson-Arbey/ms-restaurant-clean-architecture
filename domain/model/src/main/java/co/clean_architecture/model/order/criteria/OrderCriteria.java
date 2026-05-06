package co.clean_architecture.model.order.criteria;

import co.clean_architecture.model.exception.InvalidFieldException;
import co.clean_architecture.model.order.OrderStatus;
import lombok.Getter;

@Getter
public class OrderCriteria {

    private final OrderStatus status;

    private final int limit;

    private final int offset;

    private OrderCriteria(OrderStatus status, Integer limit, Integer offset) {
        if(status == null) throw new InvalidFieldException("Order status cannot be null");
        this.status = status;
        this.limit = limit == null ? 10 : limit;
        this.offset = offset == null ? 0 : offset;
    }

    public static OrderCriteria of(OrderStatus status, Integer limit, Integer offset) {
        return new OrderCriteria(status, limit, offset);
    }
}
