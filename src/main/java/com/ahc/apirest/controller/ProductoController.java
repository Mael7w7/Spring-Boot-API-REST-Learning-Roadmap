package com.ahc.apirest.controller;

import com.ahc.apirest.dto.producto.ProductoRequest;
import com.ahc.apirest.dto.producto.ProductoResponse;
import com.ahc.apirest.service.impl.ClienteServices;
import com.ahc.apirest.service.impl.ProductoServices;
import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor

public class ProductoController {
    private final ProductoServices productoServices;

    @PostMapping
    public ResponseEntity<ProductoResponse> save(@RequestBody ProductoRequest productoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServices.save(productoRequest));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponse> update(@PathVariable Long id, @RequestParam Integer cantidad) {
        return ResponseEntity.ok(productoServices.updateStock(cantidad,id));
    }
}
