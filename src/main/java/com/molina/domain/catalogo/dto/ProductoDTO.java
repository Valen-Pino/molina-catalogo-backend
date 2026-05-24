package com.molina.domain.catalogo.dto;

import java.math.BigDecimal;

public record ProductoDTO(
        Long id,
        String nombre,
        BigDecimal precio,
        BigDecimal precioPromocional,
        String categoriaNombre,
        String listadoNombre
) {}