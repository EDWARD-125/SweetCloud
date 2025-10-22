package com.sweetcloud.service.interfaces;

import com.sweetcloud.dto.ComboDTO;
import java.util.List;

public interface IComboService {

    List<ComboDTO> findAll();

    ComboDTO findById(Long id);

    ComboDTO save(ComboDTO comboDTO);

    ComboDTO update(Long id, ComboDTO comboDTO);

    void deleteById(Long id);
}