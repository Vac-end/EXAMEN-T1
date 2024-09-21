package com.example.T1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Cajero")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String email;

    @ManyToOne
    @JoinColumn(name = "estadoId")
    private Estado estado;
}
