package com.soft_arex.service.questionnaire;

import com.soft_arex.entity.Questionnaire;
import com.soft_arex.questionnaire.model.QuestionnaireRequestDTO;
import com.soft_arex.questionnaire.model.QuestionnaireResponseDTO;

import java.util.List;

public interface QuestionnaireService {

    QuestionnaireResponseDTO updateStatus(Long id);
    List<QuestionnaireResponseDTO> getAll();
    QuestionnaireResponseDTO getById(Long id);
    QuestionnaireResponseDTO create(QuestionnaireRequestDTO dto);
    QuestionnaireResponseDTO update(Long id, QuestionnaireRequestDTO dto);
    List<QuestionnaireResponseDTO> getByUser();
    void delete(Long id);
}
