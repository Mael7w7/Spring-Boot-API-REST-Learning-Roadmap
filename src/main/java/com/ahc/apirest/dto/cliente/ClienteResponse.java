package com.ahc.apirest.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String email;
    private  String telefono;

}
