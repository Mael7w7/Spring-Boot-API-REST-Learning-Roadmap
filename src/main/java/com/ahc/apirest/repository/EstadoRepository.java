package com.ahc.apirest.repository;

import com.ahc.apirest.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<NotaEntity, Long> {
}
