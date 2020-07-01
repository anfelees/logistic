package com.hiberus.logistic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order entity
 */
@Data
@Table("order_data")
public class OrderModel {
    @Id
    private Long id;
    @Column("checkout_order_id")
    private long checkoutOrderId;
    @Column("client_id")
    private long clientId;
    private LocalDateTime created;
    private String direction;
    @Column("total_amount")
    private BigDecimal totalAmount;
}
