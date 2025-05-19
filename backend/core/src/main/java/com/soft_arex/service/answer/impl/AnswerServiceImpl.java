package com.soft_arex.service.answer.impl;

import com.soft_arex.entity.*;
import com.soft_arex.repository.FieldRepository;
import com.soft_arex.repository.QuestionnaireRepository;
import com.soft_arex.repository.ResponseRepository;
import com.soft_arex.repository.UserRepository;
import com.soft_arex.answer.model.SubmitAnswerRequest;
import com.soft_arex.answer.model.AnswerDTO;
import com.soft_arex.service.answer.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final QuestionnaireRepository questionnaireRepository;
    private final FieldRepository fieldRepository;
    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;


    public void submit(SubmitAnswerRequest request) {

        Answer response = new Answer();
        if(request.getQuestionnaireId() != null) {
            Questionnaire questionnaire = questionnaireRepository.findById(request.getQuestionnaireId())
                    .orElse(null);
            response.setQuestionnaire(questionnaire);
        }else
            response.setQuestionnaire(null);


        response.setSubmittedAt(LocalDateTime.now());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && auth.getPrincipal() != "anonymousUser") {
            Long userId = ((User) auth.getPrincipal()).getId();
            response.setSubmittedBy(userRepository.findById(userId).orElse(null));
        }

        List<Long> fieldIds = request.getAnswers().stream()
                .map(AnswerDTO::getFieldId)
                .distinct()
                .toList();


        Map<Long, Field> fieldMap = fieldRepository.findAllById(fieldIds).stream()
                .collect(Collectors.toMap(Field::getId, Function.identity()));

        // валидация и маппинг ответов
        List<AnswerField> answers = new ArrayList<>();

        for (AnswerDTO dto : request.getAnswers()) {
            Field field = fieldMap.get(dto.getFieldId());
            if (field == null) {
                throw new RuntimeException("Field not found: ID " + dto.getFieldId());
            }

            if (field.isRequired() && (dto.getValue() == null || dto.getValue().isBlank())) {
                throw new RuntimeException("Required field is empty: " + field.getLabel());
            }

            AnswerField answer = new AnswerField();
            answer.setField(field);
            answer.setValue(dto.getValue());

            answers.add(answer);
        }

        response.setAnswers(answers);
        responseRepository.save(response);
    }
}

