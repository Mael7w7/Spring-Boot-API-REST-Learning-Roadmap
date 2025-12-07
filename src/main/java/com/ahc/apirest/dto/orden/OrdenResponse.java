package com.ahc.apirest.dto.orden;


import com.ahc.apirest.entity.OrdenItemEntity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder

public class OrdenResponse {
    private Long id ;
    private LocalDate fechaCreacion;
    private Long clienteId;
    private String nombre;
    private String email;

    private List<OrderItemResponse> items;

    private BigDecimal total;
}
