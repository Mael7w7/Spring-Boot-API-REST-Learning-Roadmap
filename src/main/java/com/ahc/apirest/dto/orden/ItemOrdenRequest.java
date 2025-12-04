package com.ahc.apirest.dto.orden;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ItemOrdenRequest {

    @NotNull(message = "El productoId es obligatorio")
    private Long productoId;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

}
