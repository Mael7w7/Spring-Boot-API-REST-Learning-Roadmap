package com.ahc.apirest.service;

import com.ahc.apirest.dto.orden.CrearOrdenRequest;
import com.ahc.apirest.dto.orden.OrdenResponse;

import java.util.List;

public interface IOrdenServices {

   OrdenResponse crearOrden(CrearOrdenRequest orden);
   OrdenResponse findByid(Long id);
   List<OrdenResponse> finByClienteId(Long clienteId);
   boolean delete (Long id);

}
