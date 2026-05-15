package co.clean_architecture.consumer.traceability.mapper;

import co.clean_architecture.consumer.traceability.request.RegisterTraceabilityRequest;
import co.clean_architecture.model.traceability.Traceability;

import java.time.LocalDateTime;

public class RegisterTraceabilityRequestMapper {

    public static RegisterTraceabilityRequest toRequest(Traceability traceability) {
        if(traceability == null) return null;
        return  RegisterTraceabilityRequest
                .builder()
                .status(traceability.getStatus())
                .orderId(traceability.getOrderId())
                .restaurantId(traceability.getRestaurantId())
                .employeeId(traceability.getEmployeeId())
                .changeStatusOrder(traceability.getChangeStatusOrder())
                .build();
    }
}
