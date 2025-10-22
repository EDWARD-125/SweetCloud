package com.sweetcloud.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Beverage extends Product {

    private String tamaño; // Campo propio de la clase Beverage

    // Constructor para Factory (sin tamaño)
    public Beverage(String nombre, Double precio) {
        this.setNombre(nombre);
        this.setPrecio(precio);
    }
}
