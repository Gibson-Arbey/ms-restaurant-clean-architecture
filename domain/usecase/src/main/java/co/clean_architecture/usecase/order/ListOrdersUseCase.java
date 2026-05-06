package co.clean_architecture.usecase.order;

import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.criteria.OrderCriteria;
import co.clean_architecture.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListOrdersUseCase {

    private final OrderRepository orderRepository;

    public List<Order> execute(OrderCriteria criteria){
        return orderRepository.findAllByCriteria(criteria);
    }
}
