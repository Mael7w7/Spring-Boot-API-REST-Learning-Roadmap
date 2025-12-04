package com.ahc.apirest.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El telefono es obligatorio")
    private  String telefono;


}
