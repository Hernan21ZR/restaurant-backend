package com.restaurant_backend.repository;

import com.restaurant_backend.model.Pago;

import java.util.List;

public interface IPagoRepository extends IGenericRepository<Pago, Integer> {
    List<Pago> findByVentaId(Integer ventaId);
}
