package com.soft_arex.controller;

import com.soft_arex.field.contract.FieldRestAPI;
import com.soft_arex.field.model.FieldRequestDTO;
import com.soft_arex.field.model.FieldResponseDTO;
import com.soft_arex.service.field.FieldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class FieldController implements FieldRestAPI {

    private final FieldService fieldService;

    @Override
    public Page<FieldResponseDTO> getAllFields(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return fieldService.getAll(pageable);
    }

    @Override
    public FieldResponseDTO createField(@RequestBody @Valid FieldRequestDTO dto) {
        return fieldService.create(dto);
    }

    @Override
    public FieldResponseDTO updateField(@PathVariable Long id, @RequestBody @Valid FieldRequestDTO dto) {
        return fieldService.update(id, dto);
    }

    @Override
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
