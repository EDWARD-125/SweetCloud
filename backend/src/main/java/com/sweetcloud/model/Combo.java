package com.sweetcloud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "beverage_id")
    private Beverage bebida;

    @ManyToOne
    @JoinColumn(name = "dessert_id")
    private Dessert postre;

    // ✅ Constructor para Factory
    public Combo(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo
    public Combo(String nombre, Double precio, Beverage bebida, Dessert postre) {
        this.nombre = nombre;
        this.precio = precio;
        this.bebida = bebida;
        this.postre = postre;
    }
}