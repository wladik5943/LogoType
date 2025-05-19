package com.soft_arex.service.questionnaire.impl;

import com.soft_arex.entity.Questionnaire;
import com.soft_arex.mapper.QuestionnaireMapper;
import com.soft_arex.questionnaire.model.QuestionnaireRequestDTO;
import com.soft_arex.questionnaire.model.QuestionnaireResponseDTO;
import com.soft_arex.repository.FieldRepository;
import com.soft_arex.repository.QuestionnaireRepository;
import com.soft_arex.service.field.FieldService;
import com.soft_arex.service.questionnaire.QuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;
    private final FieldRepository fieldRepository;
    private final QuestionnaireMapper questionnaireMapper;

    public List<QuestionnaireResponseDTO> getAll() {
        return questionnaireRepository.findAll().stream().map(questionnaireMapper::toResponse).toList();
    }

    public QuestionnaireResponseDTO getById(Long id) {
        return questionnaireMapper.toResponse(questionnaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questionnaire not found")));
    }

    public QuestionnaireResponseDTO create(QuestionnaireRequestDTO dto) {
        Questionnaire q = new Questionnaire();
        q.setTitle(dto.getTitle());
        q.setActive(dto.isActive());
        q.setFields(fieldRepository.findAllById(dto.getFieldIds()));
        return questionnaireMapper.toResponse(questionnaireRepository.save(q));
    }

    public QuestionnaireResponseDTO update(Long id, QuestionnaireRequestDTO dto) {
        Questionnaire q = questionnaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        q.setTitle(dto.getTitle());
        q.setActive(dto.isActive());
        q.setFields(fieldRepository.findAllById(dto.getFieldIds()));
        return questionnaireMapper.toResponse(questionnaireRepository.save(q));
    }

    public void delete(Long id) {
        questionnaireRepository.deleteById(id);
    }


}
