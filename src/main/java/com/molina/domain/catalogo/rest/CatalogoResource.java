package com.molina.domain.catalogo.rest;

import com.molina.domain.catalogo.dto.ProductoDTO;
import com.molina.domain.catalogo.entity.Categoria;
import com.molina.domain.catalogo.entity.Listado;
import com.molina.domain.catalogo.entity.Producto;
import com.molina.domain.catalogo.service.CatalogoService;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
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
    @PermitAll
    public List<ProductoDTO> listarProductos() {
        return catalogoService.listarProductos();
    }

    @POST
    @RolesAllowed("Admin")
    public Response crearProducto(ProductoDTO dto) {
        Producto productoCreado = catalogoService.crearProducto(dto);
        return Response.status(Response.Status.CREATED).entity(productoCreado).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response actualizarProducto(@PathParam("id") Long id, ProductoDTO dto) {
        try {
            Producto productoActualizado = catalogoService.actualizarProducto(id, dto);
            return Response.ok(productoActualizado).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response eliminarProducto(@PathParam("id") Long id) {
        boolean eliminado = catalogoService.eliminarProducto(id);
        if (eliminado) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/categorias")
    public List<Categoria> obtenerCategorias() {
        return Categoria.listAll(Sort.by("id"));
    }

    @GET
    @Path("/listados")
    public List<Listado> obtenerListados() {
        return Listado.listAll(Sort.by("id"));
    }

}
