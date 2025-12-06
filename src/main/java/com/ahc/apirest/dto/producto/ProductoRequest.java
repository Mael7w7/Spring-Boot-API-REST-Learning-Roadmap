package com.ahc.apirest.dto.producto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class ProductoRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1,message = "El precio debe ser mayor a 0")
    private BigDecimal precio;
    @Min(value = 1 , message = "El stock no puedes ser negativo")
    private Integer stock ;

}
