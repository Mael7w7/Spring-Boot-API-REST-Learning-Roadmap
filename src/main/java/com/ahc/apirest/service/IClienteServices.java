package com.ahc.apirest.service;

import com.ahc.apirest.dto.cliente.ClienteRequest;
import com.ahc.apirest.dto.cliente.ClienteResponse;

import java.util.List;

public interface  IClienteServices {

    ClienteResponse guardarCliente(ClienteRequest cliente);
    List<ClienteResponse> listarClientes();


}
