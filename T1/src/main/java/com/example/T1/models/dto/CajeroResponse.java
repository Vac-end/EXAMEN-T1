package com.example.T1.models.dto;

import com.example.T1.models.Estado;
import lombok.Builder;

@Builder
public record CajeroResponse(
        Long id,
        String documento,
        String nombre,
        String domicilio,
        String telefono,
        String email,
        String estado
) {
}
