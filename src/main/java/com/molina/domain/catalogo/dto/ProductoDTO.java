package com.molina.domain.catalogo.dto;

import java.math.BigDecimal;

public record ProductoDTO(
        String nombre,
        BigDecimal precio,
        BigDecimal precioPromocional,
        String categoriaNombre,
        String listado
) {

    public ProductoDTO(String nombre, BigDecimal precio, BigDecimal precioPromocional, String categoriaNombre) {
        this(nombre, precio, precioPromocional, categoriaNombre, null);
    }

}