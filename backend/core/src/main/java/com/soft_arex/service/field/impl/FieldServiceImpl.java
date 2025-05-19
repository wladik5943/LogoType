package com.soft_arex.service.field.impl;

import com.soft_arex.entity.Field;
import com.soft_arex.entity.User;
import com.soft_arex.field.model.FieldRequestDTO;
import com.soft_arex.field.model.FieldResponseDTO;
import com.soft_arex.mapper.FieldMapper;
import com.soft_arex.repository.FieldRepository;
import com.soft_arex.repository.UserRepository;
import com.soft_arex.service.field.FieldService;
import com.soft_arex.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository repository;

    private final JwtService jwtService;
    private final FieldMapper fieldMapper;

    public Page<FieldResponseDTO> getAll(Pageable pageable) {
        Page<Field> page = repository.findByUserIdOrderById(jwtService.getUserByToken().getId(),pageable);
        return page.map(fieldMapper::toResponse);
    }

    public FieldResponseDTO create(FieldRequestDTO dto) {
        Field field = new Field();
        field.setLabel(dto.getLabel());
        field.setType(dto.getType());
        field.setRequired(dto.isRequired());
        field.setActive(dto.isActive());
        field.setOptions(dto.getOptions());
        field.setUser(jwtService.getUserByToken());
        return fieldMapper.toResponse(repository.save(field));
    }

    public FieldResponseDTO update(Long id, FieldRequestDTO dto) {
        Field field = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field not found"));
        field.setLabel(dto.getLabel());
        field.setType(dto.getType());
        field.setRequired(dto.isRequired());
        field.setActive(dto.isActive());
        return fieldMapper.toResponse(repository.save(field));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
