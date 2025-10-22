package com.sweetcloud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComboDTO {
    
    private Long id;
    
    @NotBlank(message = "El nombre del combo es obligatorio")
    private String nombre;
    
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;
    
    @NotNull(message = "Debe seleccionar una bebida")
    private Long bebidaId;
    
    @NotNull(message = "Debe seleccionar un postre")
    private Long postreId;
}