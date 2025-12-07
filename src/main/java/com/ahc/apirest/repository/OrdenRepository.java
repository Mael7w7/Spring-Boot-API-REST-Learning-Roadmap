package com.ahc.apirest.repository;

import com.ahc.apirest.entity.OrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenRepository extends JpaRepository<OrdenEntity, Long> {


    List<OrdenEntity> findByCliente_Id(Long clienteId);
}
