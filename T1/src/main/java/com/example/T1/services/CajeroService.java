package com.example.T1.services;

import com.example.T1.models.Cajero;
import com.example.T1.models.Estado;
import com.example.T1.models.dto.CajeroRequest;
import com.example.T1.models.dto.CajeroResponse;
import com.example.T1.models.dto.EstadoResponse;
import com.example.T1.repositorys.CajeroRepository;
import com.example.T1.repositorys.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CajeroService {
    private final CajeroRepository cajeroRepository;
    private final EstadoRepository estadoRepository;

    public ResponseEntity<List<CajeroResponse>> getAll() {
        List<Cajero> lista = cajeroRepository.findAll();
        List<CajeroResponse> res = lista.stream().map(this::mapToCajeroResponse).toList();
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<Map<String, Object>> create(CajeroRequest cajeroRequest) {
        Map<String, Object> res = new HashMap<>();
        Optional<Estado> estadoSelect = estadoRepository.findByNombre(cajeroRequest.estado());
        Cajero nuevoCajero = new Cajero().builder()
                .documento(cajeroRequest.documento())
                .nombre(cajeroRequest.nombre())
                .domicilio(cajeroRequest.domicilio())
                .email(cajeroRequest.email())
                .telefono(cajeroRequest.telefono())
                .estado(estadoSelect.orElseThrow())
                .build();
        Cajero registrado;
        if(cajeroRequest.id() == 0) {
            registrado = cajeroRepository.save(nuevoCajero);
        }else{
            nuevoCajero.setId(cajeroRequest.id());
            registrado = cajeroRepository.save(nuevoCajero);
        }

        res.put("cajero", mapToCajeroResponse(registrado));
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<CajeroResponse> update(CajeroRequest cajeroRequest) {
        Optional<Cajero> existingCajero = cajeroRepository.findById(cajeroRequest.id());
        if (existingCajero.isPresent()) {
            Cajero cajeroToUpdate = existingCajero.get();
            cajeroToUpdate.setDocumento(cajeroRequest.documento());
            cajeroToUpdate.setNombre(cajeroRequest.nombre());
            cajeroToUpdate.setDomicilio(cajeroRequest.domicilio());
            cajeroToUpdate.setEmail(cajeroRequest.email());
            cajeroToUpdate.setTelefono(cajeroRequest.telefono());
            Optional<Estado> estado = estadoRepository.findByNombre(cajeroRequest.estado());
            cajeroToUpdate.setEstado(estado.orElseThrow());

            Cajero updateCajero = cajeroRepository.save(cajeroToUpdate);
            return ResponseEntity.ok(mapToCajeroResponse(updateCajero));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private CajeroResponse mapToCajeroResponse(Cajero cajeros) {
        return new CajeroResponse(
                cajeros.getId(),
                cajeros.getDocumento(),
                cajeros.getNombre(),
                cajeros.getDomicilio(),
                cajeros.getTelefono(),
                cajeros.getEmail(),
                cajeros.getEstado().getNombre()
        );
    }
    private EstadoResponse mapToEstadoResponse(Estado estado) {
        return new EstadoResponse(
                estado.getId(),
                estado.getNombre(),
                estado.getDescripcion()
        );
    }

    public void DarBaja(Long id) {
        Optional<Cajero> cajero = cajeroRepository.findById(id);
        Cajero CajeroEditado = cajero.get();
        CajeroEditado.setEstado(estadoRepository.findById(2L).get());
        cajeroRepository.save(CajeroEditado);
    }

    public void Restaurar(Long id) {
        Optional<Cajero> cajero = cajeroRepository.findById(id);
        Cajero CajeroEditado = cajero.get();
        CajeroEditado.setEstado(estadoRepository.findById(1L).get());
        cajeroRepository.save(CajeroEditado);
    }
}
