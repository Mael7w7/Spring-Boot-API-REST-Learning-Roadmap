package com.ahc.apirest.dto.orden;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class crearOrdenRequest {

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;

    @Size(min = 1, message = "La orden debe tener al menos un item")
    private List<ItemOrdenRequest> items;
}
