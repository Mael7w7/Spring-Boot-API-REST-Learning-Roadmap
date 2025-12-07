package com.ahc.apirest.service.impl;

import com.ahc.apirest.dto.cliente.ClienteResponse;
import com.ahc.apirest.dto.orden.CrearOrdenRequest;
import com.ahc.apirest.dto.orden.ItemOrdenRequest;
import com.ahc.apirest.dto.orden.OrdenResponse;
import com.ahc.apirest.dto.orden.OrderItemResponse;
import com.ahc.apirest.entity.ClienteEntity;
import com.ahc.apirest.entity.OrdenEntity;
import com.ahc.apirest.entity.OrdenItemEntity;
import com.ahc.apirest.entity.ProductoEntity;
import com.ahc.apirest.repository.ClienteRepository;
import com.ahc.apirest.repository.OrdenRepository;
import com.ahc.apirest.repository.ProductoRepository;
import com.ahc.apirest.service.IOrdenServices;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class OrdenServices implements IOrdenServices {

    private final OrdenRepository ordenrepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public OrdenResponse crearOrden(CrearOrdenRequest request) {

            //Validar cliente
        ClienteEntity cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado"));

        //Crear orden base
        OrdenEntity orden = new OrdenEntity();
        orden.setCliente(cliente);
        orden.setFechaCreacion(LocalDate.now());
        orden.setTotal(BigDecimal.ZERO);

         orden = ordenrepository.save(orden); //se guarda y se obtiene el id

        BigDecimal total = BigDecimal.ZERO;
        List<OrdenItemEntity> items = new ArrayList<>();

        //procesar cada item
        for (ItemOrdenRequest itemReq : request.getItems()) {
            ProductoEntity producto = productoRepository.findById(itemReq.getProductoId())
                    .orElseThrow(()-> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < itemReq.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
            }

            //Actualizar Stock

            producto.setStock(producto.getStock() - itemReq.getCantidad());
            productoRepository.save(producto);

            //Crear Item de orden
            OrdenItemEntity item = new OrdenItemEntity();
            item.setCantidad(itemReq.getCantidad());
            item.setPrecioUnitario(producto.getPrecio());
            item.setProducto(producto);

            //subtotal
            BigDecimal subtotal = producto.getPrecio()
                    .multiply(BigDecimal.valueOf(itemReq.getCantidad()));

            item.setSubtotal(subtotal);
            total = total.add(subtotal);
            items.add(item);

        }

        //agregar item a la orden
        orden.setItems(items);
        orden.setTotal(total);

        //guardar orden con items
        var orde= ordenrepository.save(orden);

        //convertir a response
        List<OrderItemResponse> itemsResponse = orden.getItems().stream()
                .map(item -> OrderItemResponse.builder()
                        .id(item.getId())
                        .productoId(item.getProducto().getId())
                        .nombreProducto(item.getProducto().getNombre())
                        .cantidad(item.getCantidad())
                        .precioUnitario(item.getPrecioUnitario())
                        .subtotal(item.getPrecioUnitario()
                                .multiply(BigDecimal.valueOf(item.getCantidad())))
                        .build()
                )
                .toList();



        return OrdenResponse.builder()
                .id(orde.getId())
                .fechaCreacion(orde.getFechaCreacion())
                .clienteId(cliente.getId())
                .nombre(cliente.getNombre())
                .email(cliente.getEmail())
                .items(itemsResponse)
                .total(total).build();
    }



    @Override
    public OrdenResponse findByid(Long id) {

        OrdenEntity orden = ordenrepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Orden no encontrado"));

        ClienteResponse cliente = ClienteResponse.builder()
                .id(orden.getCliente().getId())
                .nombre(orden.getCliente().getNombre())
                .email(orden.getCliente().getEmail())
                .build();


        List<OrderItemResponse> items = orden.getItems().stream()
                .map(r-> OrderItemResponse.builder()
                        .id(r.getId())
                        .productoId(r.getProducto().getId())
                        .nombreProducto(r.getProducto().getNombre())
                        .cantidad(r.getCantidad())
                        .precioUnitario(r.getPrecioUnitario())
                        .subtotal(r.getPrecioUnitario()
                                .multiply(BigDecimal.valueOf(r.getCantidad())))
                        .build()
                        ).collect(Collectors.toList());

        return OrdenResponse.builder()
                .id(orden.getId())
                .fechaCreacion(orden.getFechaCreacion())
                .clienteId(cliente.getId())
                .nombre(cliente.getNombre())
                .email(cliente.getEmail())
                .items(items)
                .total(orden.getTotal())
                .build();
    }

    @Override
    public List<OrdenResponse> finByClienteId(Long clienteId) {

        ClienteEntity cliente= clienteRepository.findById(clienteId)
                .orElseThrow(()->new RuntimeException("El cliente no existe"));

        List<OrdenEntity> orden = ordenrepository.findByCliente_Id(clienteId);


        return orden.stream()
                .map(or -> {

                    // 3.1. Convertir cada item a OrderItemResponse
                    List<OrderItemResponse> itemsResponse = or.getItems().stream()
                            .map(item -> OrderItemResponse.builder()
                                    .id(item.getId())
                                    .productoId(item.getProducto().getId())
                                    .nombreProducto(item.getProducto().getNombre())
                                    .cantidad(item.getCantidad())
                                    .precioUnitario(item.getPrecioUnitario())
                                    .subtotal(item.getPrecioUnitario()
                                            .multiply(BigDecimal.valueOf(item.getCantidad())))
                                    .build()
                            ).toList();

                    // 3.2. Crear la respuesta final
                    return OrdenResponse.builder()
                            .id(or.getId())
                            .fechaCreacion(or.getFechaCreacion())
                            .clienteId(cliente.getId())
                            .nombre(cliente.getNombre())
                            .email(cliente.getEmail())
                            .items(itemsResponse)
                            .total(or.getTotal())
                            .build();
                })
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        return ordenrepository.findById(id)
                .map(r-> {
                    ordenrepository.delete(r);
                    return true;
                }).orElse(false);

    }
}
