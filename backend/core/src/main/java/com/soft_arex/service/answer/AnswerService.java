package com.soft_arex.service.answer;

import com.soft_arex.answer.model.AnswerResponse;
import com.soft_arex.answer.model.SubmitAnswerRequest;
import org.springframework.data.domain.Page;

public interface AnswerService {
    void submit(SubmitAnswerRequest request);
    Page<AnswerResponse> getAnswersByTestId(Long testId, int page, int size);
}
