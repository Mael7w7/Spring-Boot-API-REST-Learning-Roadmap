package com.ahc.apirest.repository;

import com.ahc.apirest.entity.OrdenItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<OrdenItemEntity, Long> {


}
