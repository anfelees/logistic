package com.hiberus.logistic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Order value object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1426055589085297957L;

    @Min(value = 1, message = "clientId id is mandatory")
    private int clientId;
    @NotNull(message = "date is mandatory")
    private LocalDateTime date;
    @NotBlank(message = "direction is mandatory")
    private String direction;
    @NotEmpty(message = "products is mandatory")
    private List< @Valid Product> products;
}
