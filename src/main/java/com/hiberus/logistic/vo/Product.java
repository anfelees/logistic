package com.hiberus.logistic.vo;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Product value object
 */
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = -6043374017810861493L;

    @Min(value = 1, message = "Product id is mandatory")
    private long id;
    @Min(value = 1, message = "quantity is mandatory")
    private int quantity;
    @NotNull(message = "const is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "const is mandatory")
    private BigDecimal cost;
}
