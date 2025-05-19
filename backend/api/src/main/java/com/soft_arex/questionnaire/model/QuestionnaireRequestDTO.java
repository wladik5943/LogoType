package com.soft_arex.questionnaire.model;

import lombok.Data;

import java.util.List;

@Data
public class QuestionnaireRequestDTO {
    private String title;
    private boolean active;
    private List<Long> fieldIds;
}
