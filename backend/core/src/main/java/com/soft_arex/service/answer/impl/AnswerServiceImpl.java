package com.soft_arex.service.answer.impl;

import com.soft_arex.answer.model.AnswerFieldDTO;
import com.soft_arex.answer.model.AnswerResponse;
import com.soft_arex.controller.AnswerNotifier;
import com.soft_arex.entity.*;
import com.soft_arex.exeption.UserException;
import com.soft_arex.mapper.AnswerMapper;
import com.soft_arex.repository.FieldRepository;
import com.soft_arex.repository.QuestionnaireRepository;
import com.soft_arex.repository.AnswerRepository;
import com.soft_arex.repository.UserRepository;
import com.soft_arex.answer.model.SubmitAnswerRequest;
import com.soft_arex.service.answer.AnswerService;
import com.soft_arex.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final AnswerNotifier answerNotifier;
    private final AnswerMapper answerMapper;
    private final JwtService jwtService;

    public Page<AnswerResponse> getAnswersByTestId(Long testId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return answerRepository.findByQuestionnaireId(testId, pageable)
                .map(answerMapper::toResponse);
    }





    public void submit(SubmitAnswerRequest request) {

        Answer response = new Answer();
        if(request.getQuestionnaireId() != null) {
            Questionnaire questionnaire = questionnaireRepository.findById(request.getQuestionnaireId())
                    .orElseThrow(() -> new UserException("Questionnaire not found"));
            response.setQuestionnaire(questionnaire);
        }else
           throw new UserException("QuestionnaireId is null");


        response.setSubmittedAt(LocalDateTime.now());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && auth.getPrincipal() != "anonymousUser") {
            response.setSubmittedBy(jwtService.getUserByToken());
        }

        List<Long> fieldIds = request.getAnswers().stream()
                .map(AnswerFieldDTO::getFieldId)
                .distinct()
                .toList();


        Map<Long, Field> fieldMap = fieldRepository.findAllById(fieldIds).stream()
                .collect(Collectors.toMap(Field::getId, Function.identity()));

        // валидация и маппинг ответов
        List<AnswerField> answers = new ArrayList<>();

        for (AnswerFieldDTO dto : request.getAnswers()) {
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
        answerRepository.save(response);
        answerNotifier.notifyAnswerCount((int)answerRepository.countByQuestionnaireId(request.getQuestionnaireId()),response);
    }
}

