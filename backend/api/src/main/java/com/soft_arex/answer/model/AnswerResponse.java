package com.soft_arex.answer.model;

import com.soft_arex.field.model.FieldResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class AnswerResponse {
    private Long id;
    private Long questionnaireId;
    private String questionnaireName;
    private List<AnswerFieldDTO> answers;
    private List<FieldResponseDTO> fields;
}
