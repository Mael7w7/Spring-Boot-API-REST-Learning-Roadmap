package com.ahc.apirest.service;

import com.ahc.apirest.dto.TareaDTO;

import java.util.List;
import java.util.Optional;

public interface ITareaServices {
    TareaDTO create(TareaDTO dto);
    List<TareaDTO> findAll();
    Optional<TareaDTO> findbyId(Long id);
    TareaDTO update(Long id,TareaDTO dto);
    boolean delete(Long id);

}
