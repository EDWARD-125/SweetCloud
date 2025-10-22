package com.sweetcloud.service;

import com.sweetcloud.dto.BeverageDTO;
import com.sweetcloud.exception.ResourceNotFoundException;
import com.sweetcloud.model.Beverage;
import com.sweetcloud.repository.BeverageRepository;
import com.sweetcloud.service.interfaces.IBeverageService;
import com.sweetcloud.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BeverageService implements IBeverageService {

    @Autowired
    private BeverageRepository beverageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BeverageDTO> listar() {
        return beverageRepository.findAll().stream()
                .map(MapperUtil::toBeverageDTO)  // ✅ Usando MapperUtil
                .collect(Collectors.toList());
    }

    @Override
    public BeverageDTO crear(BeverageDTO beverageDTO) {
        Beverage beverage = MapperUtil.toBeverageEntity(beverageDTO);  // ✅ Usando MapperUtil
        Beverage guardada = beverageRepository.save(beverage);
        return MapperUtil.toBeverageDTO(guardada);  // ✅ Usando MapperUtil
    }

    @Override
    public BeverageDTO actualizar(Long id, BeverageDTO beverageDTO) {
        Beverage existente = beverageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bebida no encontrada con id: " + id));
        
        existente.setNombre(beverageDTO.getNombre());
        existente.setTamaño(beverageDTO.getTamaño());
        existente.setPrecio(beverageDTO.getPrecio());
        
        Beverage actualizada = beverageRepository.save(existente);
        return MapperUtil.toBeverageDTO(actualizada);  // ✅ Usando MapperUtil
    }

    @Override
    public void eliminar(Long id) {
        if (!beverageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bebida no encontrada con id: " + id);
        }
        beverageRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BeverageDTO obtenerPorId(Long id) {
        Beverage beverage = beverageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bebida no encontrada con id: " + id));
        return MapperUtil.toBeverageDTO(beverage);  // ✅ Usando MapperUtil
    }
}