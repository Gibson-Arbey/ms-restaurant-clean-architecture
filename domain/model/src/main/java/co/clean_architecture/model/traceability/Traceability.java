package co.clean_architecture.model.traceability;

import co.clean_architecture.model.order.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Traceability {

    private final OrderStatus status;

    private final Long orderId;

    private final Long restaurantId;

    private final Long employeeId;

    private final LocalDateTime changeStatusOrder;

    public Traceability(OrderStatus status, Long orderId, Long restaurantId, Long employeeId, LocalDateTime changeStatusOrder) {
        this.status = status;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.employeeId =  employeeId;
        this.changeStatusOrder = changeStatusOrder;
    }

    public static Traceability create(OrderStatus status, Long orderId, Long restaurantId, Long employeeId, LocalDateTime changeStatusOrder) {
        return new Traceability(status, orderId, restaurantId, employeeId, changeStatusOrder);
    }
}
