package com.hiberus.logistic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Class that transport the application version info
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiVersionMessage implements Serializable {

    private static final long serialVersionUID = 3790230746735720773L;

    private String version;
}
