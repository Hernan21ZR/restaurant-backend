package com.restaurant_backend.service;

import com.restaurant_backend.dto.VentaDTO;
import com.restaurant_backend.model.Venta;

public interface IVentaService extends IGenericService<Venta, Integer> {
    Venta generarVenta(Integer pedidoId) throws Exception;
}

