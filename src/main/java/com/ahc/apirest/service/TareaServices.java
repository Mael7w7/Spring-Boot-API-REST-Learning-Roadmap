package com.ahc.apirest.service;

import com.ahc.apirest.dto.TareaDTO;
import com.ahc.apirest.entity.Tarea;
import com.ahc.apirest.repository.TareaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TareaServices implements ITareaServices{
    //inyeccion de dependencia
    private final TareaRepository repository;

    @Override
    public TareaDTO create(TareaDTO dto) {
        Tarea tarea = new Tarea();
        tarea.setNombre(dto.getNombre());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFecha(dto.getFecha());
        tarea.setCompletado(false);

        var tar = repository.save(tarea);

        return TareaDTO.builder()
                .nombre(tar.getNombre())
                .descripcion(tar.getDescripcion())
                .fecha(tar.getFecha())
                .completado(tar.isCompletado())
                .build();
    }

    @Override
    public List<TareaDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(
                        r-> TareaDTO.builder()
                               .nombre(r.getNombre())
                               .fecha(r.getFecha())
                               .descripcion(r.getDescripcion())
                               .completado(r.isCompletado())
                               .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<TareaDTO> findbyId(Long id) {
        return repository.findById(id).map(
                r-> TareaDTO.builder()
                        .nombre(r.getNombre())
                        .fecha(r.getFecha())
                        .descripcion(r.getDescripcion())
                        .completado(r.isCompletado())
                        .build()
        );
    }

    @Override
    public TareaDTO update(Long id, TareaDTO dto) {

        Tarea tarea = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe"));

        tarea.setNombre(dto.getNombre());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFecha(dto.getFecha());
        tarea.setCompletado(dto.isCompletado());

        var tare =repository.save(tarea);

        return TareaDTO.builder()
                .nombre(tare.getNombre())
                .descripcion(tare.getDescripcion())
                .fecha(tare.getFecha())
                .completado(tare.isCompletado()).build();
    }



    @Override
    public boolean delete(Long id) {
            return repository.findById(id).map(
                    r->{
                        repository.delete(r);
                        return true;
                    }
            ).orElse(false);
    }
}
