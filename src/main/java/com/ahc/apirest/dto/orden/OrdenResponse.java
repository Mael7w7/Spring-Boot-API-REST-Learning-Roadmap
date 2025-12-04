package com.ahc.apirest.dto.orden;

import com.ahc.apirest.entity.ClienteEntity;
import com.ahc.apirest.entity.OrdenItemEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrdenResponse {
    private Long id ;
    private LocalDate fechaCreacion;

    private Long clienteId;
    private String nombre;
    private String email;

    private List<OrdenItemEntity> items;

    private BigDecimal total;
}
