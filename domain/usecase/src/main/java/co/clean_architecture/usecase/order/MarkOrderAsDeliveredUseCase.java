package co.clean_architecture.usecase.order;

import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.exception.CustomerNotOwnerOfOrderException;
import co.clean_architecture.model.order.exception.EmployeeNotAssignedToOrderException;
import co.clean_architecture.model.order.exception.InvalidOrderPinException;
import co.clean_architecture.model.order.exception.OrderNotExistsException;
import co.clean_architecture.model.order.gateways.OrderRepository;
import co.clean_architecture.usecase.order.command.MarkOrderAsDeliveredCommand;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class MarkOrderAsDeliveredUseCase {

    private final OrderRepository orderRepository;

    public void execute(Long orderId, Long employeeId, MarkOrderAsDeliveredCommand command){
        Order order = orderRepository.findById(orderId);

        if (order == null) {
            throw new OrderNotExistsException("Order Not Exists");
        }

        if (!Objects.equals(order.getEmployeeId(), employeeId)) {
            throw new EmployeeNotAssignedToOrderException("Employee is not assigned to the order");
        }

        if (!Objects.equals(order.getPin(), command.pin())) {
            throw new InvalidOrderPinException("Invalid order pin");
        }

        orderRepository.save(Order.markOrderAsDelivered(order));

    }
}
