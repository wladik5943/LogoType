package com.soft_arex.service.questionnaire.impl;

import com.soft_arex.entity.Field;
import com.soft_arex.entity.Questionnaire;
import com.soft_arex.entity.User;
import com.soft_arex.exeption.UserException;
import com.soft_arex.mapper.QuestionnaireMapper;
import com.soft_arex.questionnaire.model.QuestionnaireRequestDTO;
import com.soft_arex.questionnaire.model.QuestionnaireResponseDTO;
import com.soft_arex.repository.FieldRepository;
import com.soft_arex.repository.QuestionnaireRepository;
import com.soft_arex.repository.UserRepository;
import com.soft_arex.service.field.FieldService;
import com.soft_arex.service.questionnaire.QuestionnaireService;
import com.soft_arex.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;
    private final FieldRepository fieldRepository;
    private final QuestionnaireMapper questionnaireMapper;
    private final JwtService jwtService;
    private final UserRepository  userRepository;

    @Override
    public QuestionnaireResponseDTO updateStatus(Long id) {
        Questionnaire q = questionnaireRepository.findById(id)
                .orElseThrow(() -> new UserException("`questionnaire` not found"));
        q.setActive(!q.isActive());
        return questionnaireMapper.toResponse(questionnaireRepository.save(q));
    }

    public List<QuestionnaireResponseDTO> getAll() {
        return questionnaireRepository.findAll().stream().map(questionnaireMapper::toResponse).toList();
    }

    public QuestionnaireResponseDTO getById(Long id) {
        return questionnaireMapper.toResponse(questionnaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questionnaire not found")));
    }

    public QuestionnaireResponseDTO create(QuestionnaireRequestDTO dto) {
        Questionnaire q = new Questionnaire();
        q.setUser(jwtService.getUserByToken());

        q.setTitle(dto.getTitle());
        q.setActive(dto.isActive());

        List<Field> fields = fieldRepository.findAllById(dto.getFieldIds());
        if (fields.contains(null)) {
            throw new IllegalArgumentException("Некоторые поля не найдены по id");
        }
        q.setFields(fields);

        Questionnaire saved = questionnaireRepository.save(q);
        Questionnaire initialized = questionnaireRepository.findWithFieldsById(saved.getId())
                .orElseThrow(() -> new RuntimeException("Failed to fetch created questionnaire"));
        return questionnaireMapper.toResponse(initialized);
    }

    public QuestionnaireResponseDTO update(Long id, QuestionnaireRequestDTO dto) {
        Questionnaire q = questionnaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        q.setTitle(dto.getTitle());
        q.setFields(fieldRepository.findAllWithOptionsByIds(dto.getFieldIds()));
        return questionnaireMapper.toResponse(questionnaireRepository.save(q));
    }

    @Override
    public List<QuestionnaireResponseDTO> getByUser() {
        Long id = jwtService.getUserByToken().getId();
        return questionnaireRepository.findByUserId(id).stream()
                .map(questionnaireMapper::toResponse).toList();
    }

    public void delete(Long id) {
        questionnaireRepository.deleteById(id);
    }


}
