package com.sweetcloud.service.interfaces;

import com.sweetcloud.dto.BeverageDTO;
import java.util.List;

public interface IBeverageService {

    List<BeverageDTO> listar();

    BeverageDTO crear(BeverageDTO beverageDTO);

    BeverageDTO actualizar(Long id, BeverageDTO beverageDTO);

    void eliminar(Long id);

    BeverageDTO obtenerPorId(Long id);
}