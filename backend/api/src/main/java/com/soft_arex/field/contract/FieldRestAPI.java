package com.soft_arex.field.contract;

import com.soft_arex.field.model.FieldRequestDTO;
import com.soft_arex.field.model.FieldResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/fields")
public interface FieldRestAPI {

    @GetMapping
    Page<FieldResponseDTO> getAllFields(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size);


    @PostMapping
    FieldResponseDTO createField(@RequestBody @Valid FieldRequestDTO dto);

    @PutMapping("/{id}")
    FieldResponseDTO updateField(@PathVariable Long id, @RequestBody @Valid FieldRequestDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteField(@PathVariable Long id);
}
