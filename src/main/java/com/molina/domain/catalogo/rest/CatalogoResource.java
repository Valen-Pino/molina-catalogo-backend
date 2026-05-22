package com.molina.domain.catalogo.rest;

import com.molina.domain.catalogo.dto.ProductoDTO;
import com.molina.domain.catalogo.entity.Producto;
import com.molina.domain.catalogo.service.CatalogoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/catalogo")
public class CatalogoResource {

    @Inject
    CatalogoService catalogoService;

    @GET
    public List<ProductoDTO> listarProductos(@QueryParam("listado") String listado) {
        return catalogoService.obtenerProductosPorListado(listado);
    }

    @POST
    public Response crearProducto(ProductoDTO dto) {
        Producto productoCreado = catalogoService.crearProducto(dto);
        return Response.status(Response.Status.CREATED).entity(productoCreado).build();
    }

}
