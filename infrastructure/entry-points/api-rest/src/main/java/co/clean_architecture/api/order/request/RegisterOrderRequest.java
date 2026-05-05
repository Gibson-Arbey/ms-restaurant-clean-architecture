package co.clean_architecture.api.order.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterOrderRequest {
    private Long restaurantId;
    private List<RegisterOrderDishRequest> dishes;
}
