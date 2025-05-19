package com.soft_arex.service.questionnaire;

import com.soft_arex.questionnaire.model.QuestionnaireRequestDTO;
import com.soft_arex.questionnaire.model.QuestionnaireResponseDTO;

import java.util.List;

public interface QuestionnaireService {

    List<QuestionnaireResponseDTO> getAll();
    QuestionnaireResponseDTO getById(Long id);
    QuestionnaireResponseDTO create(QuestionnaireRequestDTO dto);
    QuestionnaireResponseDTO update(Long id, QuestionnaireRequestDTO dto);
    void delete(Long id);
}
