package co.clean_architecture.usecase.order;

import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.exception.CustomerNotOwnerOfOrderException;
import co.clean_architecture.model.order.exception.OrderNotExistsException;
import co.clean_architecture.model.order.gateways.OrderRepository;
import co.clean_architecture.model.traceability.Traceability;
import co.clean_architecture.model.traceability.gateways.TraceabilityRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
public class MarkOrderAsCancelUseCase {

    private final OrderRepository orderRepository;
    private final TraceabilityRepository traceabilityRepository;

    public void execute(Long orderId, Long customerId) {
        Order order = orderRepository.findById(orderId);

        if(order == null) {
            throw new OrderNotExistsException("Order Not Exists");
        }

        if(!Objects.equals(order.getCustomerId(), customerId)) {
            throw new CustomerNotOwnerOfOrderException("Customer is not the owner of the order");
        }

        orderRepository.save(Order.markOrderAsCancel(order));
        traceabilityRepository.save(Traceability.create(
                order.getStatus(),
                order.getId(),
                order.getRestaurantId(),
                null,
                LocalDateTime.now()
        ));
    }
}
