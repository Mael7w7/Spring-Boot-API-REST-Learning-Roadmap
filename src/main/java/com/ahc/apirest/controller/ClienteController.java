package com.ahc.apirest.controller;

import com.ahc.apirest.dto.cliente.ClienteRequest;
import com.ahc.apirest.dto.cliente.ClienteResponse;
import com.ahc.apirest.service.impl.ClienteServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {
    private final ClienteServices clienteServices;

    @PostMapping
    public ResponseEntity<ClienteResponse> save(@RequestBody ClienteRequest clienteRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteServices.guardarCliente(clienteRequest));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> findAll() {
        List<ClienteResponse> response = clienteServices.listarClientes();
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
