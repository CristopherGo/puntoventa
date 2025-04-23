package com.tienda.puntoventa.service;

import com.tienda.puntoventa.model.DetalleVenta;
import com.tienda.puntoventa.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImpl(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardarDetalle(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }
}
