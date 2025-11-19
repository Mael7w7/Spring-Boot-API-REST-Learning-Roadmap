package com.ahc.apirest.Controller;

import com.ahc.apirest.dto.TareaDTO;
import com.ahc.apirest.service.TareaServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
@AllArgsConstructor

public class TareaController {
    //INYECCION DE DEPENDENCIA

    private final TareaServices tareaServices;

    @GetMapping
    public ResponseEntity<List<TareaDTO>> listarTareas() {
        List<TareaDTO> lista = tareaServices.findAll();

        if (!lista.isEmpty()) {
            return ResponseEntity.ok(lista);
        }
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> findById(@PathVariable Long id) {
        return tareaServices.findbyId(id).map(
                ResponseEntity::ok
        ).orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<TareaDTO> save(@RequestBody TareaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaServices.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaDTO> update(@PathVariable Long id, @RequestBody TareaDTO dto) {
       return ResponseEntity.status(HttpStatus.OK).body(tareaServices.update(id, dto));
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(tareaServices.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PatchMapping("/{id}/completar")
    public ResponseEntity<Void> completar(@PathVariable Long id) {
        if (tareaServices.marcadoCompletado(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}/pendiente")
    public ResponseEntity<Void> pendiente(@PathVariable Long id) {
        if (tareaServices.marcadoPendiente(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/completadas")
    public ResponseEntity<List<TareaDTO>> listaCompletado() {
        List<TareaDTO> lista = tareaServices.listadoTareaCompleta();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/pendiente")
    public ResponseEntity<List<TareaDTO>> listaPendiente() {
        List<TareaDTO> lista = tareaServices.listadoTareaPendiente();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TareaDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<TareaDTO> lista = tareaServices.buscarPorNombre(nombre);
        return ResponseEntity.ok(lista);
    }

}
