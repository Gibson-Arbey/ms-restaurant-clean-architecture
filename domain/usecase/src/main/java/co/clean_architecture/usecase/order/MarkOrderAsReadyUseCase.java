package co.clean_architecture.usecase.order;

import co.clean_architecture.model.notification.Notification;
import co.clean_architecture.model.notification.gateways.NotificationRepository;
import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.exception.EmployeeNotAssignedToOrderException;
import co.clean_architecture.model.order.exception.OrderNotExistsException;
import co.clean_architecture.model.order.gateways.OrderRepository;
import co.clean_architecture.model.traceability.Traceability;
import co.clean_architecture.model.traceability.gateways.TraceabilityRepository;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.exception.UserNotFoundException;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@RequiredArgsConstructor
public class MarkOrderAsReadyUseCase {

    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final TraceabilityRepository traceabilityRepository;

    public void execute(Long orderId, Long employeeId) {
        Order order = orderRepository.findById(orderId);

        if(order == null){
            throw new OrderNotExistsException("Order not found");
        }

        if(Objects.equals(order.getEmployeeId(), employeeId)){
            throw new EmployeeNotAssignedToOrderException("Employee is not assigned to the order");
        }

        User customer = userRepository.findById(order.getCustomerId());

        if(customer == null){
            throw new UserNotFoundException("Customer not found");
        }

        // Send a notification to the customer
        String recipient = customer.getPhoneNumber();
        Integer pin = generatePin();

        String message = "Your order " + orderId +
                " is ready for pickup! Please provide the following PIN when collecting your order: " +
                pin;
        notificationRepository.sendNotification(Notification.create(recipient, message));

        orderRepository.save(Order.markOrdenAsReady(order, pin));
        traceabilityRepository.save(Traceability.create(
                order.getStatus(),
                order.getId(),
                order.getRestaurantId(),
                employeeId,
                LocalDateTime.now()
        ));
    }

    private Integer generatePin() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}
