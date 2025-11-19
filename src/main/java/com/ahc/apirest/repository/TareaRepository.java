package com.ahc.apirest.repository;

import com.ahc.apirest.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
