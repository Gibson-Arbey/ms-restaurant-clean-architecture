package co.clean_architecture.api.order.response;

import co.clean_architecture.model.order.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderResponse {

    private Long id;
    private Long restaurantId;
    private Long customerId;
    private Long employeeId;
    private Integer pin;
    private OrderStatus status;
    private List<OrderDishResponse> dishes;
}