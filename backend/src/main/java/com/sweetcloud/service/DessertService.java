package com.sweetcloud.service;

import com.sweetcloud.dto.DessertDTO;
import com.sweetcloud.exception.ResourceNotFoundException;
import com.sweetcloud.model.Dessert;
import com.sweetcloud.repository.DessertRepository;
import com.sweetcloud.service.interfaces.IDessertService;
import com.sweetcloud.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DessertService implements IDessertService {

    @Autowired
    private DessertRepository dessertRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DessertDTO> findAll() {
        return dessertRepository.findAll().stream()
                .map(MapperUtil::toDessertDTO)  // ✅ Usando MapperUtil
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DessertDTO findById(Long id) {
        Dessert dessert = dessertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postre no encontrado con id: " + id));
        return MapperUtil.toDessertDTO(dessert);  // ✅ Usando MapperUtil
    }

    @Override
    public DessertDTO save(DessertDTO dessertDTO) {
        Dessert dessert = MapperUtil.toDessertEntity(dessertDTO);  // ✅ Usando MapperUtil
        Dessert guardado = dessertRepository.save(dessert);
        return MapperUtil.toDessertDTO(guardado);  // ✅ Usando MapperUtil
    }

    @Override
    public DessertDTO update(Long id, DessertDTO dessertDTO) {
        Dessert existente = dessertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postre no encontrado con id: " + id));
        
        existente.setNombre(dessertDTO.getNombre());
        existente.setTipo(dessertDTO.getTipo());
        existente.setPrecio(dessertDTO.getPrecio());
        
        Dessert actualizado = dessertRepository.save(existente);
        return MapperUtil.toDessertDTO(actualizado);  // ✅ Usando MapperUtil
    }

    @Override
    public void deleteById(Long id) {
        if (!dessertRepository.existsById(id)) {
            throw new ResourceNotFoundException("Postre no encontrado con id: " + id);
        }
        dessertRepository.deleteById(id);
    }
}