package com.ahc.apirest.dto;

import com.ahc.apirest.entity.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotaDTO {
    private String titulo;
    private String contenido;
    private Estado estado;

}
