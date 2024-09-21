package com.example.T1.controllers;


import com.example.T1.models.dto.CajeroRequest;
import com.example.T1.models.dto.CajeroResponse;
import com.example.T1.services.CajeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Cajeros")
@RequiredArgsConstructor
public class CajeroController {
    private final CajeroService cajeroService;

    @GetMapping
    public ResponseEntity<List<CajeroResponse>> GetAll(){
        return cajeroService.getAll();
    }
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody CajeroRequest cajeroRequest){
        return cajeroService.create(cajeroRequest);
    }
    @PutMapping
    public ResponseEntity<CajeroResponse> Update(@RequestBody CajeroRequest cajeroRequest){
        return cajeroService.update(cajeroRequest);
    }

    @GetMapping("/Baja/{id}")
    public void delete(@PathVariable("id") Long id){
        cajeroService.DarBaja(id);
    }
    @GetMapping("/Restore/{id}")
    public void restaurar(@PathVariable("id") Long id){
        cajeroService.Restaurar(id);
    }

}
