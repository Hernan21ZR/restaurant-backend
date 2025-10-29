package com.restaurant_backend.service.implementation;


import com.restaurant_backend.model.Venta;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IVentaRepository;
import com.restaurant_backend.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VentaService extends GenericService<Venta, Integer> implements IVentaService {
    private final IVentaRepository repo;

    @Override
    protected IGenericRepository<Venta, Integer> getRepo() {
        return repo;
    }
}
