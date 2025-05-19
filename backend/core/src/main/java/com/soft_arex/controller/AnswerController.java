package com.soft_arex.controller;

import com.soft_arex.answer.model.SubmitAnswerRequest;
import com.soft_arex.service.answer.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/answer")
@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public void saveAnswer(@RequestBody SubmitAnswerRequest request) {
        answerService.submit(request);
    }
}
