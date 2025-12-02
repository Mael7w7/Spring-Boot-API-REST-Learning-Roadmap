package com.ahc.apirest.repository;

import com.ahc.apirest.dto.NotaDTO;
import com.ahc.apirest.entity.Estado;
import com.ahc.apirest.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    List<NotaEntity> findByEstado(Estado estado);
}
