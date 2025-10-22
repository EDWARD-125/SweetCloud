package com.sweetcloud.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Dessert extends Product {
    
    private String tipo;

    // ✅ Constructor para Factory
    public Dessert(String nombre, Double precio) {
        this.setNombre(nombre);
        this.setPrecio(precio);
    }

    // Constructor completo
    public Dessert(String nombre, Double precio, String tipo) {
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.tipo = tipo;
    }
}