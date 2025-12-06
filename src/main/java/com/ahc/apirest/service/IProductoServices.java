package com.ahc.apirest.service;

import com.ahc.apirest.dto.producto.ProductoRequest;
import com.ahc.apirest.dto.producto.ProductoResponse;

public interface IProductoServices {
    ProductoResponse save(ProductoRequest productoRequest);
    ProductoResponse updateStock(Integer cantidad,Long id);


}
