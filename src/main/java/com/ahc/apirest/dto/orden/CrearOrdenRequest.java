package com.ahc.apirest.dto.orden;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearOrdenRequest {

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;

    @Size(min = 1, message = "La orden debe tener al menos un item")
    private List<ItemOrdenRequest> items;
}
