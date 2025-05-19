package com.soft_arex.controller;

import com.soft_arex.questionnaire.contract.QuestionnaireRestApi;
import com.soft_arex.questionnaire.model.QuestionnaireRequestDTO;
import com.soft_arex.questionnaire.model.QuestionnaireResponseDTO;
import com.soft_arex.service.questionnaire.QuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class QuestionnaireController implements QuestionnaireRestApi {

    private final QuestionnaireService questionnaireService;


    @Override
    public List<QuestionnaireResponseDTO> getAll() {
        return questionnaireService.getAll();
    }

    @Override
    public QuestionnaireResponseDTO getById(Long id) {
        return questionnaireService.getById(id);
    }

    @Override
    public QuestionnaireResponseDTO create(QuestionnaireRequestDTO dto) {
        return questionnaireService.create(dto);
    }

    @Override
    public QuestionnaireResponseDTO update(Long id, QuestionnaireRequestDTO dto) {
        return questionnaireService.update(id, dto);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        questionnaireService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
