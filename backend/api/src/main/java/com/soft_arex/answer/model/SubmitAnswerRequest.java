package com.soft_arex.answer.model;

import lombok.Data;

import java.util.List;

@Data
public class SubmitAnswerRequest {
    private Long questionnaireId;
    private List<AnswerFieldDTO> answers;

}

