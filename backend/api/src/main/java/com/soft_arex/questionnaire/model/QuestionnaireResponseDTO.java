package com.soft_arex.questionnaire.model;

import com.soft_arex.field.model.FieldResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class QuestionnaireResponseDTO {
    private Long id;
    private String title;
    private boolean active;
    private List<FieldResponseDTO> fields;
}
