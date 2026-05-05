package co.clean_architecture.jpa.entity;

import co.clean_architecture.model.order.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "orde_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rest_id", nullable = false)
    private Long restaurantId;

    @Column(name = "cust_id", nullable = false)
    private Long customerId;

    @Column(name = "empl_id")
    private Long employeeId;

    @Column(name = "orde_pin")
    private Integer pin;

    @Enumerated(EnumType.STRING)
    @Column(name = "orde_status", nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDishEntity> dishes;
}
