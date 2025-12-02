package com.ahc.apirest.service;

import com.ahc.apirest.dto.NotaDTO;
import com.ahc.apirest.entity.Estado;

import java.util.List;

public interface INotaService {
    NotaDTO save(NotaDTO dto);
    List<NotaDTO> findAll();
    NotaDTO findById(Long id);
    boolean delete (Long id);
    List<NotaDTO> findByEstado(Estado estado);
    NotaDTO update(Long id,NotaDTO dto);


}
