package com.sweetcloud.util;

import com.sweetcloud.dto.BeverageDTO;
import com.sweetcloud.dto.ComboDTO;
import com.sweetcloud.dto.DessertDTO;
import com.sweetcloud.model.Beverage;
import com.sweetcloud.model.Combo;
import com.sweetcloud.model.Dessert;

/**
 * Utilidad de mapeo simple DTO <-> Entity.
 * Métodos estáticos para evitar necesidad de instanciar.
 */
public final class MapperUtil {

    private MapperUtil() { /* util class */ }

    // -------------------
    // Beverage mappings
    // -------------------
    public static BeverageDTO toBeverageDTO(Beverage b) {
        if (b == null) return null;
        BeverageDTO dto = new BeverageDTO();
        dto.setId(b.getId());
        dto.setNombre(b.getNombre());
        dto.setTamaño(b.getTamaño());
        dto.setPrecio(b.getPrecio());
        return dto;
    }

    public static Beverage toBeverageEntity(BeverageDTO dto) {
        if (dto == null) return null;
        Beverage b = new Beverage();
        b.setId(dto.getId());
        b.setNombre(dto.getNombre());
        b.setTamaño(dto.getTamaño());
        b.setPrecio(dto.getPrecio());
        return b;
    }

    // -------------------
    // Dessert mappings
    // -------------------
    public static DessertDTO toDessertDTO(Dessert d) {
        if (d == null) return null;
        DessertDTO dto = new DessertDTO();
        dto.setId(d.getId());
        dto.setNombre(d.getNombre());
        dto.setTipo(d.getTipo());
        dto.setPrecio(d.getPrecio());
        return dto;
    }

    public static Dessert toDessertEntity(DessertDTO dto) {
        if (dto == null) return null;
        Dessert d = new Dessert();
        d.setId(dto.getId());
        d.setNombre(dto.getNombre());
        d.setTipo(dto.getTipo());
        d.setPrecio(dto.getPrecio());
        return d;
    }

    // -------------------
    // Combo mappings
    // -------------------
    public static ComboDTO toComboDTO(Combo c) {
        if (c == null) return null;
        ComboDTO dto = new ComboDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setPrecio(c.getPrecio());
        if (c.getBebida() != null) dto.setBebidaId(c.getBebida().getId());
        if (c.getPostre() != null) dto.setPostreId(c.getPostre().getId());
        return dto;
    }

    /**
     * Crea la entidad Combo a partir de un ComboDTO. Dado que Combo tiene referencias
     * a Beverage y Dessert, estas deben ser provistas por el llamador (ej.: servicio).
     *
     * @param dto       DTO con los datos básicos del combo
     * @param beverage  entidad Beverage asociada (puede ser null)
     * @param dessert   entidad Dessert asociada (puede ser null)
     * @return Combo entidad construida
     */
    public static Combo toComboEntity(ComboDTO dto, Beverage beverage, Dessert dessert) {
        if (dto == null) return null;
        Combo c = new Combo();
        c.setId(dto.getId());
        c.setNombre(dto.getNombre());
        c.setPrecio(dto.getPrecio());
        c.setBebida(beverage);
        c.setPostre(dessert);
        return c;
    }
}
