package com.ahc.apirest.Controller;

import com.ahc.apirest.dto.NotaDTO;
import com.ahc.apirest.entity.Estado;
import com.ahc.apirest.entity.NotaEntity;
import com.ahc.apirest.service.NotaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota")
@AllArgsConstructor
public class NotaController {
    private final NotaService notaService;

    @PostMapping
    public ResponseEntity<NotaDTO> save(@RequestBody NotaDTO notaDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(notaService.save(notaDTO));
    }


    @GetMapping
    public ResponseEntity<List<NotaDTO>> findAll() {
        List<NotaDTO> notaDTOS = notaService.findAll();
        if (!notaDTOS.isEmpty()) {
            return ResponseEntity.ok(notaDTOS);
        }
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(notaService.findById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(notaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<NotaDTO>> findByEstado(@PathVariable Estado estado) {
        List<NotaDTO> notaDTOS = notaService.findByEstado(estado);
        if (!notaDTOS.isEmpty()) {
            return ResponseEntity.ok(notaDTOS);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> update(@PathVariable Long id, @RequestBody NotaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(notaService.update(id, dto));

    }

}
