package com.ahc.apirest.service.impl;

import com.ahc.apirest.dto.producto.ProductoRequest;
import com.ahc.apirest.dto.producto.ProductoResponse;
import com.ahc.apirest.entity.ProductoEntity;
import com.ahc.apirest.repository.ProductoRepository;
import com.ahc.apirest.service.IProductoServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductoServices implements IProductoServices {

    private final ProductoRepository repository;

    @Override
    public ProductoResponse save(ProductoRequest productoRequest) {

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setNombre(productoRequest.getNombre());
        productoEntity.setPrecio(productoRequest.getPrecio());
        productoEntity.setStock(productoRequest.getStock());

        var tar = repository.save(productoEntity);
        return ProductoResponse.builder()
                .id(tar.getId())
                .nombre(tar.getNombre())
                .precio(tar.getPrecio())
                .stock(tar.getStock())
                .build();
    }

    @Override
    public ProductoResponse updateStock(Integer Cantidad, Long id) {
        ProductoEntity productoEntity = repository.findById(id)
                .orElseThrow(()->new RuntimeException("producto not found"));
        int nuevoStock = productoEntity.getStock() + Cantidad;

        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        productoEntity.setStock(nuevoStock);

       var tar = repository.save(productoEntity);


        return ProductoResponse.builder()
                .id(tar.getId())
                .nombre(tar.getNombre())
                .precio(tar.getPrecio())
                .stock(tar.getStock()).build();

    }
}
