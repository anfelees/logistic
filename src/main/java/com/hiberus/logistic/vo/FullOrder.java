package com.hiberus.logistic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents a complete order
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FullOrder extends Order implements Serializable {

    private static final long serialVersionUID = 5423876541985283734L;
    @Min(value = 1, message = "orderId is mandatory")
    private long orderId;
    @NotNull(message = "totalOrderAmount is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "totalOrderAmount is mandatory")
    private BigDecimal totalOrderAmount;

    /**
     * Class constructor
     * @param order order
     * @param totalOrderAmount total order amount
     */
    public FullOrder(Order order, BigDecimal totalOrderAmount, long orderId) {
        super(order.getClientId(), order.getDate(), order.getDirection(), order.getProducts());
        this.orderId = orderId;
        this.totalOrderAmount = totalOrderAmount;
    }
}
