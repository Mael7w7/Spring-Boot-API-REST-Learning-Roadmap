package com.ahc.apirest.service;

import com.ahc.apirest.dto.TareaDTO;
import com.ahc.apirest.entity.Tarea;
import com.ahc.apirest.repository.TareaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter @Setter

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
    @Override
    public boolean marcadoCompletado(Long id) {
        Optional<Tarea> tarea = repository.findById(id);
        if(tarea.isPresent()){
            Tarea tarea1 = tarea.get();
            tarea1.setCompletado(true);
            repository.save(tarea1);
            return true;
        }
        return false;
    }

    @Override
    public boolean marcadoPendiente(Long id) {
        Optional<Tarea> tarea = repository.findById(id);

        if(tarea.isPresent()){
            Tarea tarea1 = tarea.get();
            tarea1.setCompletado(false);
            repository.save(tarea1);
            return true;
        }
        return false;
    }

    public List<TareaDTO> listadoTareaCompleta() {

        return repository.findByCompletadoTrue().stream()
                .map(
                        r-> TareaDTO.builder()
                                .nombre(r.getNombre())
                                .fecha(r.getFecha())
                                .completado((r.isCompletado()))
                                .descripcion((r.getDescripcion())).build()

                ).collect(Collectors.toList());

    }

    public List<TareaDTO> listadoTareaPendiente() {
        return repository.findByCompletadoFalse().stream()
                .map(
                        r-> TareaDTO.builder()
                                .nombre(r.getNombre())
                                .descripcion(r.getDescripcion())
                                .fecha(r.getFecha())
                                .completado(r.isCompletado())
                                .build()
                ).collect(Collectors.toList());

    }
    public List<TareaDTO> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(
                        r->TareaDTO.builder()
                                .nombre(r.getNombre())
                                .descripcion(r.getDescripcion())
                                .fecha(r.getFecha())
                                .completado(r.isCompletado())
                                .build()
                ).collect(Collectors.toList());


    }

}
