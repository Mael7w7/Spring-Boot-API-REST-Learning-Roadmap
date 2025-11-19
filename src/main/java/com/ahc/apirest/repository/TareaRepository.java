package com.ahc.apirest.repository;

import com.ahc.apirest.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByCompletadoTrue();
    List<Tarea> findByCompletadoFalse();

    @Query("SELECT t FROM Tarea t WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Tarea> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
}
