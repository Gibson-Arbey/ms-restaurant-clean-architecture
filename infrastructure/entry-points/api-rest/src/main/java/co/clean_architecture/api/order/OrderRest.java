package co.clean_architecture.api.order;

import co.clean_architecture.api.config.SecurityUtil;
import co.clean_architecture.api.order.mapper.OrderResponseMapper;
import co.clean_architecture.api.order.mapper.RegisterOrderRequestMapper;
import co.clean_architecture.api.order.request.RegisterOrderRequest;
import co.clean_architecture.api.order.response.OrderResponse;
import co.clean_architecture.usecase.order.RegisterOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderRest {

    private final RegisterOrderUseCase registerOrderUseCase;

    @PostMapping
    public ResponseEntity<OrderResponse> registerOrder(@RequestBody RegisterOrderRequest request) {
        Long customerId = SecurityUtil.getCurrentUserId();
        return ResponseEntity.status(HttpStatus.CREATED).body(
                OrderResponseMapper.toResponse(
                        registerOrderUseCase.execute(
                                RegisterOrderRequestMapper.toCommand(request, customerId)
                        )
                )
        );
    }
}