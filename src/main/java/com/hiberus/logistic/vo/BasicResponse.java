package com.hiberus.logistic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Application basic response value object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse implements Serializable {

    private static final long serialVersionUID = -3038146455484744077L;

    private boolean success;
    private String message;
}
