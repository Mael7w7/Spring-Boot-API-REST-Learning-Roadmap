package com.ahc.apirest.dto.orden;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long id;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

}
