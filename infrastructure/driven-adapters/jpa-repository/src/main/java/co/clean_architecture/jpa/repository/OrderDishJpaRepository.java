package co.clean_architecture.jpa.repository;

import co.clean_architecture.jpa.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDishJpaRepository extends JpaRepository<OrderDishEntity, Long> {
}
