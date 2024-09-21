package com.example.T1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Estado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estado {
    @Id
    private Long id;
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<Cajero> cajeros;
}
