package com.sweetcloud.controller;

import com.sweetcloud.dto.ComboDTO;
import com.sweetcloud.service.interfaces.IComboService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
public class ComboController {

    private final IComboService comboService;

    public ComboController(IComboService comboService) {
        this.comboService = comboService;
    }

    @GetMapping
    public ResponseEntity<List<ComboDTO>> getAllCombos() {
        return ResponseEntity.ok(comboService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComboDTO> getComboById(@PathVariable Long id) {
        return ResponseEntity.ok(comboService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ComboDTO> createCombo(@Valid @RequestBody ComboDTO comboDTO) {
        ComboDTO creado = comboService.save(comboDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComboDTO> updateCombo(
            @PathVariable Long id,
            @Valid @RequestBody ComboDTO comboDTO) {
        return ResponseEntity.ok(comboService.update(id, comboDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCombo(@PathVariable Long id) {
        comboService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}