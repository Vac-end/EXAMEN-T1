package com.example.T1.models.dto;

import lombok.Builder;

public record CajeroRequest(
        Long id,
        String documento,
        String nombre,
        String domicilio,
        String email,
        String telefono,
        String estado

) {
}
