package com.ahc.apirest.service.impl;

import com.ahc.apirest.dto.cliente.ClienteRequest;
import com.ahc.apirest.dto.cliente.ClienteResponse;
import com.ahc.apirest.entity.ClienteEntity;
import com.ahc.apirest.repository.ClienteRepository;
import com.ahc.apirest.service.IClienteServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServices implements IClienteServices {
    private final ClienteRepository repository;

    @Override
    public ClienteResponse guardarCliente(ClienteRequest cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setTelefono(cliente.getTelefono());

        var tar = repository.save(clienteEntity);

        return ClienteResponse.builder()
                .id(tar.getId())
                .nombre(tar.getNombre())
                .email(tar.getEmail())
                .telefono(tar.getTelefono())
                .build();
    }

    @Override
    public List<ClienteResponse> listarClientes() {
        return repository.findAll().stream()
                .map(r-> ClienteResponse.builder()
                        .id(r.getId())
                        .nombre(r.getNombre())
                        .email(r.getEmail())
                        .telefono(r.getTelefono())
                        .build()).collect(Collectors.toList());
    }


}
