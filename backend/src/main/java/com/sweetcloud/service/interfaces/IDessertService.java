package com.sweetcloud.service.interfaces;

import com.sweetcloud.dto.DessertDTO;
import java.util.List;

public interface IDessertService {

    List<DessertDTO> findAll();

    DessertDTO findById(Long id);

    DessertDTO save(DessertDTO dessertDTO);

    DessertDTO update(Long id, DessertDTO dessertDTO);

    void deleteById(Long id);
}