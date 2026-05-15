package co.clean_architecture.usecase.order;

import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.exception.OrderNotExistsException;
import co.clean_architecture.model.order.gateways.OrderRepository;
import co.clean_architecture.model.restaurantemployee.exception.EmployeeNotInRestaurantException;
import co.clean_architecture.model.restaurantemployee.gateways.RestaurantEmployeeRepository;
import co.clean_architecture.model.traceability.Traceability;
import co.clean_architecture.model.traceability.gateways.TraceabilityRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AssignAnEmployeeUseCase {

    private final OrderRepository orderRepository;
    private final RestaurantEmployeeRepository restaurantEmployeeRepository;
    private final TraceabilityRepository traceabilityRepository;

    public void execute(Long orderId, Long employeeId){
        Order order = orderRepository.findById(orderId);

        if(order == null){
            throw new OrderNotExistsException("Order not found");
        }

        if(!restaurantEmployeeRepository.existsByRestaurantIdAndEmployeeId(order.getRestaurantId(), employeeId)){
            throw new EmployeeNotInRestaurantException("Employee does not work in the restaurant");
        }

        orderRepository.save(Order.assignEmployee(order,employeeId));
        traceabilityRepository.save(Traceability.create(
                order.getStatus(),
                order.getId(),
                order.getRestaurantId(),
                employeeId,
                LocalDateTime.now()
        ));
    }
}
