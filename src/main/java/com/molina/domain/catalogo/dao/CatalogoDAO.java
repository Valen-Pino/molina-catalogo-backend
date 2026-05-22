package com.molina.domain.catalogo.dao;

import com.molina.domain.catalogo.dto.ProductoDTO;
import com.molina.domain.catalogo.entity.Categoria;
import com.molina.domain.catalogo.entity.Listado;
import com.molina.domain.catalogo.entity.Producto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CatalogoDAO {

    public List<ProductoDTO> obtenerProductosPorListado(Listado listado) {

        List<Producto> productos = Producto.find("listado", listado).list();

        return productos.stream()
                .map(p ->
                    new ProductoDTO(
                            p.getNombre(),
                            p.getPrecio(),
                            p.getPrecioPromocional(),
                            p.getCategoria().getNombre()
                    )
                )
                .toList();
    }

    public Producto insertarProducto(ProductoDTO dto) {

        Producto producto = new Producto();
        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setPrecioPromocional(dto.precioPromocional());

        Categoria categoria = Categoria.find("nombre", dto.categoriaNombre()).firstResult();
        producto.setCategoria(categoria);

        producto.setListado(Listado.valueOf(dto.listado()));

        producto.persist();

        return producto;

    }

}
