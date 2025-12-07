package com.ahc.apirest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_detalle")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrdenItemEntity {
    //Esta entidad representa el detalle de un producto en una orden
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;


}
