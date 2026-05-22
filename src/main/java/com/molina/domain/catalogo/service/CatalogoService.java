package com.molina.domain.catalogo.service;

import com.molina.domain.catalogo.dao.CatalogoDAO;
import com.molina.domain.catalogo.dto.ProductoDTO;
import com.molina.domain.catalogo.entity.Listado;
import com.molina.domain.catalogo.entity.Producto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CatalogoService {

    @Inject
    CatalogoDAO dao;

    public List<ProductoDTO> obtenerProductosPorListado(String listado) {
        return dao.obtenerProductosPorListado(Listado.valueOf(listado));
    }

    @Transactional
    public Producto crearProducto(ProductoDTO productoDTO) {
        return dao.insertarProducto(productoDTO);
    }

}
