package com.soft_arex.answer.model;

import lombok.Data;

import java.util.List;

@Data
public class AnswerResponseMessage {
    private Long id;
    private Long questionnaireId;
    private List<AnswerFieldDTO> answers;
    private String questionnaireName;
}
