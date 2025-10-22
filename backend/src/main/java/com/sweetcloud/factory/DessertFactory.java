package com.sweetcloud.factory;

import com.sweetcloud.model.Beverage;
import com.sweetcloud.model.Combo;
import com.sweetcloud.model.Dessert;

/**
 * Fábrica concreta que crea objetos de tipo Dessert.
 */
public class DessertFactory extends AbstractFactory {

    @Override
    public Beverage createBeverage(String name, double price) {
        return null; // No aplica para esta fábrica
    }

    @Override
    public Dessert createDessert(String name, double price) {
        return new Dessert(name, price);
    }

    @Override
    public Combo createCombo(String name) {
        return null; // No aplica para esta fábrica
    }
}
