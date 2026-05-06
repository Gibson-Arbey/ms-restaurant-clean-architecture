package co.clean_architecture.api.order;

import co.clean_architecture.api.config.SecurityUtil;
import co.clean_architecture.api.order.mapper.OrderResponseMapper;
import co.clean_architecture.api.order.mapper.RegisterOrderRequestMapper;
import co.clean_architecture.api.order.request.RegisterOrderRequest;
import co.clean_architecture.api.order.response.OrderResponse;
import co.clean_architecture.model.order.OrderStatus;
import co.clean_architecture.model.order.criteria.OrderCriteria;
import co.clean_architecture.usecase.order.ListOrdersUseCase;
import co.clean_architecture.usecase.order.RegisterOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderRest {

    private final RegisterOrderUseCase registerOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;

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

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(
            @RequestParam("status")OrderStatus status,
            @RequestParam("limit") Integer limit,
            @RequestParam("offset") Integer offset
            ) {
        OrderCriteria criteria = OrderCriteria.of(status, limit, offset);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        listOrdersUseCase.execute(criteria)
                                .stream()
                                .map(OrderResponseMapper::toResponse)
                                .toList()
        );
    }
}