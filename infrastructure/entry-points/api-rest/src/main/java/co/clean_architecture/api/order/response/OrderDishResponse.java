package co.clean_architecture.api.order.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDishResponse {

    private Long dishId;
    private Integer quantity;
}