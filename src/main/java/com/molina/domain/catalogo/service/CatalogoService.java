package com.molina.domain.catalogo.service;

import com.molina.domain.catalogo.dto.ProductoDTO;
import com.molina.domain.catalogo.entity.Categoria;
import com.molina.domain.catalogo.entity.Listado;
import com.molina.domain.catalogo.entity.Producto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CatalogoService {

    public List<ProductoDTO> listarProductos() {

        List<Producto> productos = Producto.findAll().list();

        return productos.stream()
                .map(p ->
                        new ProductoDTO(
                                p.getId(),
                                p.getNombre(),
                                p.getPrecio(),
                                p.getPrecioPromocional(),
                                p.getCategoria().getNombre(),
                                p.getListado().getNombre()
                        )
                )
                .toList();
    }

    @Transactional
    public Producto crearProducto(ProductoDTO dto) {

        Producto producto = new Producto();

        populate(producto, dto);
        producto.persist();

        return producto;

    }

    @Transactional
    public boolean eliminarProducto(Long id) {
        return Producto.deleteById(id);
    }

    @Transactional
    public Producto actualizarProducto(Long id, ProductoDTO dto) {

        Producto producto = Producto.findById(id);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe");
        }

        populate(producto, dto);

        return producto;

    }

    private void populate(Producto producto, ProductoDTO dto) {

        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setPrecioPromocional(dto.precioPromocional());

        Categoria categoria = Categoria.find("nombre", dto.categoriaNombre()).firstResult();
        producto.setCategoria(categoria);
        Listado listado = Listado.find("nombre", dto.listadoNombre()).firstResult();
        producto.setListado(listado);

    }

}
