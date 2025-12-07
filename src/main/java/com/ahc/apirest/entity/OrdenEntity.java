package com.ahc.apirest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orden")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id ;

    @CreationTimestamp
    private LocalDate fechaCreacion;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "orden_id")
    private List<OrdenItemEntity> items;


    private BigDecimal total;


}
