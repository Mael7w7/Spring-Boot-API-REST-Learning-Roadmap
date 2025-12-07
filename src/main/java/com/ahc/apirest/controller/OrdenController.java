package com.ahc.apirest.controller;

import com.ahc.apirest.dto.orden.CrearOrdenRequest;
import com.ahc.apirest.dto.orden.OrdenResponse;
import com.ahc.apirest.service.impl.ClienteServices;
import com.ahc.apirest.service.impl.OrdenServices;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
@AllArgsConstructor
public class OrdenController {
    private final OrdenServices ordenServices;

    @PostMapping
    public ResponseEntity<OrdenResponse> crearOrden(@RequestBody CrearOrdenRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenServices.crearOrden(request));
    }

   @GetMapping("/{id}")
    public ResponseEntity<OrdenResponse> getOrden(@PathVariable Long id){
    return ResponseEntity.ok(ordenServices.findByid(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<OrdenResponse>> getOrdenesCleienteId(@PathVariable Long clienteId){
        return ResponseEntity.ok(ordenServices.finByClienteId(clienteId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id){
        if(ordenServices.delete(id)){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok().build();
    }
}
