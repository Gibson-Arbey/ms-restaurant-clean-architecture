package co.clean_architecture.jpa.repository;

import co.clean_architecture.jpa.entity.OrderEntity;
import co.clean_architecture.model.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Query("""
        SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END
        FROM OrderEntity o
        WHERE o.customerId = :customerId AND o.status IN :status
    """)
    boolean  existsByCustomerIdAndStatusIn(
            @Param("customerId") Long customerId,
            @Param("status") List<OrderStatus> status);

    @Query("""
        SELECT o
        FROM OrderEntity o
        WHERE o.status = :status
        ORDER BY o.id
        LIMIT :limit OFFSET :offset 
    """)
    List<OrderEntity> findAllByCriteria(
            @Param("status") OrderStatus status,
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}
