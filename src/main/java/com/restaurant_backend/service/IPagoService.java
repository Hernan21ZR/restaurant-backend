package com.restaurant_backend.service;

import com.restaurant_backend.dto.PagoDTO;
import com.restaurant_backend.model.Pago;

public interface IPagoService extends IGenericService<Pago,Integer>{
    Pago registrarPago(PagoDTO dto) throws Exception;
}

