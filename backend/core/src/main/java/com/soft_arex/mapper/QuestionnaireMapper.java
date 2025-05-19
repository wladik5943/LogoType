package com.soft_arex.mapper;

import com.soft_arex.entity.Questionnaire;
import com.soft_arex.questionnaire.model.QuestionnaireResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionnaireMapper {

    QuestionnaireResponseDTO toResponse(Questionnaire q);

}
