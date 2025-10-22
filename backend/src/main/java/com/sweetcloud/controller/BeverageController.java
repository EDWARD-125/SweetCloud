package com.sweetcloud.controller;

import com.sweetcloud.dto.BeverageDTO;
import com.sweetcloud.service.interfaces.IBeverageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beverages")
public class BeverageController {

    private final IBeverageService beverageService;

    public BeverageController(IBeverageService beverageService) {
        this.beverageService = beverageService;
    }

    @GetMapping
    public ResponseEntity<List<BeverageDTO>> getAllBeverages() {
        return ResponseEntity.ok(beverageService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeverageDTO> getBeverageById(@PathVariable Long id) {
        return ResponseEntity.ok(beverageService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<BeverageDTO> createBeverage(@Valid @RequestBody BeverageDTO beverageDTO) {
        BeverageDTO creada = beverageService.crear(beverageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeverageDTO> updateBeverage(
            @PathVariable Long id,
            @Valid @RequestBody BeverageDTO beverageDTO) {
        return ResponseEntity.ok(beverageService.actualizar(id, beverageDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeverage(@PathVariable Long id) {
        beverageService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}