package com.soft_arex.mapper;

import com.soft_arex.answer.model.AnswerFieldDTO;
import com.soft_arex.entity.AnswerField;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerFieldDTOMapper {
    AnswerFieldDTO toDTO(AnswerField field);
}
