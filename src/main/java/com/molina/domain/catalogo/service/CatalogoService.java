package com.molina.domain.catalogo.service;

import com.molina.domain.catalogo.entity.Producto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CatalogoService {

    public List<Producto> obtenerTodos() {
        return Producto.listAll();
    }

    @Transactional
    public Producto crearProducto(Producto producto) {
        producto.persist();
        return producto;
    }

}
