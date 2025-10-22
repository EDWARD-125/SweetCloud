package com.sweetcloud.service;

import com.sweetcloud.dto.ComboDTO;
import com.sweetcloud.exception.ResourceNotFoundException;
import com.sweetcloud.model.Beverage;
import com.sweetcloud.model.Combo;
import com.sweetcloud.model.Dessert;
import com.sweetcloud.repository.BeverageRepository;
import com.sweetcloud.repository.ComboRepository;
import com.sweetcloud.repository.DessertRepository;
import com.sweetcloud.service.interfaces.IComboService;
import com.sweetcloud.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComboService implements IComboService {

    @Autowired
    private ComboRepository comboRepository;

    @Autowired
    private BeverageRepository beverageRepository;

    @Autowired
    private DessertRepository dessertRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ComboDTO> findAll() {
        return comboRepository.findAll().stream()
                .map(MapperUtil::toComboDTO)  // ✅ Usando MapperUtil
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ComboDTO findById(Long id) {
        Combo combo = comboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Combo no encontrado con id: " + id));
        return MapperUtil.toComboDTO(combo);  // ✅ Usando MapperUtil
    }

    @Override
    public ComboDTO save(ComboDTO comboDTO) {
        Beverage bebida = beverageRepository.findById(comboDTO.getBebidaId())
                .orElseThrow(() -> new ResourceNotFoundException("Bebida no encontrada con id: " + comboDTO.getBebidaId()));
        
        Dessert postre = dessertRepository.findById(comboDTO.getPostreId())
                .orElseThrow(() -> new ResourceNotFoundException("Postre no encontrado con id: " + comboDTO.getPostreId()));

        Combo combo = MapperUtil.toComboEntity(comboDTO, bebida, postre);  // ✅ Usando MapperUtil
        Combo guardado = comboRepository.save(combo);
        return MapperUtil.toComboDTO(guardado);  // ✅ Usando MapperUtil
    }

    @Override
    public ComboDTO update(Long id, ComboDTO comboDTO) {
        Combo existente = comboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Combo no encontrado con id: " + id));

        Beverage bebida = beverageRepository.findById(comboDTO.getBebidaId())
                .orElseThrow(() -> new ResourceNotFoundException("Bebida no encontrada con id: " + comboDTO.getBebidaId()));
        
        Dessert postre = dessertRepository.findById(comboDTO.getPostreId())
                .orElseThrow(() -> new ResourceNotFoundException("Postre no encontrado con id: " + comboDTO.getPostreId()));

        existente.setNombre(comboDTO.getNombre());
        existente.setPrecio(comboDTO.getPrecio());
        existente.setBebida(bebida);
        existente.setPostre(postre);

        Combo actualizado = comboRepository.save(existente);
        return MapperUtil.toComboDTO(actualizado);  // ✅ Usando MapperUtil
    }

    @Override
    public void deleteById(Long id) {
        if (!comboRepository.existsById(id)) {
            throw new ResourceNotFoundException("Combo no encontrado con id: " + id);
        }
        comboRepository.deleteById(id);
    }
}   