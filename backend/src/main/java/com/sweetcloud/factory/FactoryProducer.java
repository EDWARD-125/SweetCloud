package com.sweetcloud.factory;

/**
 * Clase encargada de entregar la fábrica adecuada
 * según el tipo de producto solicitado.
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String type) {
        if (type == null) {
            return null;
        }

        switch (type.toLowerCase()) {
            case "beverage":
                return new BeverageFactory();
            case "dessert":
                return new DessertFactory();
            case "combo":
                return new ComboFactory();
            default:
                return null;
        }
    }
}
