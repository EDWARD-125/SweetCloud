package com.sweetcloud.util;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

    public static void validarPositivo(Double valor, String campo) {
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser mayor a 0");
        }
    }

    public static void validarNoVacio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío");
        }
    }

    public static void validarNoNulo(Object valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException(campo + " no puede ser nulo");
        }
    }
}