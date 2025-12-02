package com.restaurant_backend.service;

import com.restaurant_backend.dto.PedidoDTO;
import com.restaurant_backend.model.Pedido;

public interface IPedidoService extends IGenericService<Pedido,Integer>{
    Pedido saveFromDto(PedidoDTO dto) throws Exception;
    Pedido updateFromDto(PedidoDTO dto, Integer id) throws Exception;

    // agrega esto:
    Pedido updateEstado(Integer id, String estadoNuevo) throws Exception;
}
