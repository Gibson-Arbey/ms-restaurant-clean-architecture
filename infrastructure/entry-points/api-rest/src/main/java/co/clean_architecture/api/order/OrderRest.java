package co.clean_architecture.api.order;

import co.clean_architecture.api.config.SecurityUtil;
import co.clean_architecture.api.order.mapper.MarkOrderAsDeliveredRequestMapper;
import co.clean_architecture.api.order.mapper.OrderResponseMapper;
import co.clean_architecture.api.order.mapper.RegisterOrderRequestMapper;
import co.clean_architecture.api.order.request.MarkOrderAsDeliveredRequest;
import co.clean_architecture.api.order.request.RegisterOrderRequest;
import co.clean_architecture.api.order.response.OrderResponse;
import co.clean_architecture.model.order.OrderStatus;
import co.clean_architecture.model.order.criteria.OrderCriteria;
import co.clean_architecture.usecase.order.*;
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
    private final AssignAnEmployeeUseCase assignAnEmployeeUseCase;
    private final MarkOrderAsReadyUseCase markOrderAsReadyUseCase;
    private final MarkOrderAsCancelUseCase markOrderAsCancelUseCase;
    private final MarkOrderAsDeliveredUseCase markOrderAsDeliveredUseCase;

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
            @RequestParam("restaurantId") Long restaurantId,
            @RequestParam("status")OrderStatus status,
            @RequestParam("limit") Integer limit,
            @RequestParam("offset") Integer offset
            ) {
        Long employeeId = SecurityUtil.getCurrentUserId();
        OrderCriteria criteria = OrderCriteria.of(restaurantId, status, limit, offset);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        listOrdersUseCase.execute(employeeId, criteria)
                                .stream()
                                .map(OrderResponseMapper::toResponse)
                                .toList()
        );
    }

    @PatchMapping("/{id}/assign-employee")
    public ResponseEntity<Void> assignAnEmployee(@PathVariable Long id) {
        Long employeeId = SecurityUtil.getCurrentUserId();
        assignAnEmployeeUseCase.execute(id, employeeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}/mark-ready")
    public ResponseEntity<Void> markOrdenAsReady(@PathVariable Long id) {
        Long employeeId = SecurityUtil.getCurrentUserId();
        markOrderAsReadyUseCase.execute(id, employeeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}/mark-cancel")
    public ResponseEntity<Void> markOrderAsCancel(@PathVariable Long id) {
        Long employeeId = SecurityUtil.getCurrentUserId();
        markOrderAsCancelUseCase.execute(id, employeeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}/mark-delivered")
    public ResponseEntity<Void> markOrderAsDelivered(@PathVariable Long id, @RequestBody MarkOrderAsDeliveredRequest request) {
        Long employeeId = SecurityUtil.getCurrentUserId();
        markOrderAsDeliveredUseCase.execute(id, employeeId, MarkOrderAsDeliveredRequestMapper.toCommand(request));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}