package com.ahc.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class TareaDTO {

    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private boolean completado;


}
