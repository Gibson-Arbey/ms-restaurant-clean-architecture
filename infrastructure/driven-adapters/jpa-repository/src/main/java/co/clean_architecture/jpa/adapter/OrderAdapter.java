package co.clean_architecture.jpa.adapter;

import co.clean_architecture.jpa.mapper.OrderMapper;
import co.clean_architecture.jpa.repository.OrderJpaRepository;
import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.OrderStatus;
import co.clean_architecture.model.order.criteria.OrderCriteria;
import co.clean_architecture.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    @Transactional
    public Order save(Order order) {
        var entity = OrderMapper.toEntity(order);

        var saved = orderJpaRepository.save(entity);

        return OrderMapper.toDomain(saved);
    }

    @Override
    public boolean existsByCustomerIdAndStatusIn(Long customerId, List<OrderStatus> status) {
        return orderJpaRepository.existsByCustomerIdAndStatusIn(customerId, status);
    }

    @Override
    public List<Order> findAllByCriteria(OrderCriteria criteria) {
        return orderJpaRepository
                .findAllByCriteria(
                        criteria.getStatus(),
                        criteria.getLimit(),
                        criteria.getOffset())
                .stream()
                .map(OrderMapper::toDomain)
                .toList();
    }

    @Override
    public Order findById(Long id) {
        return OrderMapper.toDomain(orderJpaRepository.findById(id).orElse(null));
    }
}
