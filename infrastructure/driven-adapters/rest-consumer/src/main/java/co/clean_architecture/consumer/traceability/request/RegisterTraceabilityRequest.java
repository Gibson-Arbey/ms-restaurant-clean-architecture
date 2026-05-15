package co.clean_architecture.consumer.traceability.request;

import co.clean_architecture.model.order.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RegisterTraceabilityRequest {

    private OrderStatus status;

    private Long orderId;

    private Long restaurantId;

    private Long employeeId;

    private LocalDateTime changeStatusOrder;
}
