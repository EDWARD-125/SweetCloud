package com.sweetcloud.factory;

import com.sweetcloud.model.Beverage;
import com.sweetcloud.model.Dessert;
import com.sweetcloud.model.Combo;

/**
 * Clase abstracta que define los métodos de creación
 * para los diferentes tipos de productos.
 */
public abstract class AbstractFactory {

    public abstract Beverage createBeverage(String name, double price);
    public abstract Dessert createDessert(String name, double price);
    public abstract Combo createCombo(String name);
}
