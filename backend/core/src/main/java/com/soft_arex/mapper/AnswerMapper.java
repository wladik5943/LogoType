package com.soft_arex.mapper;

import com.soft_arex.answer.model.AnswerFieldDTO;
import com.soft_arex.answer.model.AnswerResponse;
import com.soft_arex.answer.model.AnswerResponseMessage;
import com.soft_arex.entity.Answer;
import com.soft_arex.entity.AnswerField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(source = "questionnaire.id", target = "questionnaireId")
    @Mapping(source = "questionnaire.title", target = "questionnaireName")
    AnswerResponseMessage toMessage(Answer answer);

    @Mapping(source = "questionnaire.id", target = "questionnaireId")
    @Mapping(source = "questionnaire.title", target = "questionnaireName")
    @Mapping(source = "questionnaire.fields", target = "fields")
    AnswerResponse toResponse(Answer answer);

    @Mapping(source = "field.id", target = "fieldId")
    AnswerFieldDTO map(AnswerField field);
}
