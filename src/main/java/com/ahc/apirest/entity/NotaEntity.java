package com.ahc.apirest.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name ="nota")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class NotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(nullable = false)
    private String contenido;
    private Estado estado;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;
}
