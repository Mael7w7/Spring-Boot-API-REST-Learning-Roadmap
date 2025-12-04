package com.ahc.apirest.dto.cliente;

import lombok.Data;

@Data

public class ClienteResponse {
    private Long id;
    private String nombre;
    private String email;
    private  String telefono;

}
