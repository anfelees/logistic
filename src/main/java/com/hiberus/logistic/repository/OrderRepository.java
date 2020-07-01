package com.hiberus.logistic.repository;

import com.hiberus.logistic.model.OrderModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

/**
 * Interface to execute operations on the order_data table
 */
public interface OrderRepository extends R2dbcRepository<OrderModel, Long> {
}
