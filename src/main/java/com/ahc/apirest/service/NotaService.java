package com.ahc.apirest.service;

import com.ahc.apirest.dto.NotaDTO;
import com.ahc.apirest.entity.Estado;
import com.ahc.apirest.entity.NotaEntity;
import com.ahc.apirest.repository.NotaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
public class NotaService implements INotaService{
    private final NotaRepository notaRepository;


    @Override
    public NotaDTO save(NotaDTO dto) {
        NotaEntity nota = new NotaEntity();
        nota.setTitulo(dto.getTitulo());
        nota.setContenido(dto.getContenido());
        nota.setEstado(dto.getEstado());

        notaRepository.save(nota);

        return NotaDTO.builder()
                .titulo(nota.getTitulo())
                .contenido(nota.getContenido())
                .estado(nota.getEstado())
                .build();
    }

    @Override
    public List<NotaDTO> findAll() {
        return notaRepository.findAll()
                .stream()
                .map(r-> NotaDTO.builder()
                        .titulo(r.getTitulo())
                        .contenido(r.getContenido())
                        .estado(r.getEstado())
                        .build()).collect(Collectors.toList());
    }



    @Override
    public NotaDTO findById(Long id) {
        return notaRepository.findById(id)
                .map(r-> NotaDTO.builder()
                        .titulo(r.getTitulo())
                        .contenido(r.getContenido())
                        .estado(r.getEstado())
                        .build()
                ).orElseThrow(()-> new RuntimeException("nota no encontrada"));

    }

    @Override
    public boolean delete(Long id) {
        return notaRepository.findById(id)
                .map(r->{
                    notaRepository.delete(r);
                    return  true;

                }).orElse(false);

    }

    @Override
    public List<NotaDTO> findByEstado(Estado estado) {
        return notaRepository.findByEstado(estado).stream()
                .map(r-> NotaDTO.builder()
                        .titulo(r.getTitulo())
                        .estado(r.getEstado())
                        .contenido(r.getContenido())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public NotaDTO update(Long id,NotaDTO dto) {

        NotaEntity nota = notaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("nota no encontrada"));
        nota.setTitulo(dto.getTitulo());
        nota.setContenido(dto.getContenido());
        nota.setEstado(dto.getEstado());
        notaRepository.save(nota);

        return NotaDTO.builder()
                .titulo(nota.getTitulo())
                .contenido(nota.getContenido())
                .estado(nota.getEstado())
                .build();

    }
}
