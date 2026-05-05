package co.clean_architecture.model.order.gateways;

import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.OrderStatus;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    boolean existsByCustomerIdAndStatusIn(Long customerId, List<OrderStatus> status);
}
