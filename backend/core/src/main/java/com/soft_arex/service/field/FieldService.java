package com.soft_arex.service.field;


import com.soft_arex.field.model.FieldRequestDTO;
import com.soft_arex.field.model.FieldResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FieldService {
    Page<FieldResponseDTO> getAll(Pageable pageable);
    FieldResponseDTO create(FieldRequestDTO dto);
    FieldResponseDTO update(Long id, FieldRequestDTO dto);
    void delete(Long id);

}
