package com.sweetcloud.controller;

import com.sweetcloud.dto.DessertDTO;
import com.sweetcloud.service.interfaces.IDessertService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desserts")
public class DessertController {

    private final IDessertService dessertService;

    public DessertController(IDessertService dessertService) {
        this.dessertService = dessertService;
    }

    @GetMapping
    public ResponseEntity<List<DessertDTO>> getAllDesserts() {
        return ResponseEntity.ok(dessertService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DessertDTO> getDessertById(@PathVariable Long id) {
        return ResponseEntity.ok(dessertService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DessertDTO> createDessert(@Valid @RequestBody DessertDTO dessertDTO) {
        DessertDTO creado = dessertService.save(dessertDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DessertDTO> updateDessert(
            @PathVariable Long id,
            @Valid @RequestBody DessertDTO dessertDTO) {
        return ResponseEntity.ok(dessertService.update(id, dessertDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDessert(@PathVariable Long id) {
        dessertService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}