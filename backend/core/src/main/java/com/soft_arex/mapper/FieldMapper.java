package com.soft_arex.mapper;

import com.soft_arex.entity.Field;
import com.soft_arex.field.model.FieldRequestDTO;
import com.soft_arex.field.model.FieldResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    FieldResponseDTO toResponse(Field field);
    Field toEntity(FieldRequestDTO request);
}
