package com.soft_arex.controller;

import com.soft_arex.answer.model.AnswerResponseMessage;
import com.soft_arex.entity.Answer;
import com.soft_arex.mapper.AnswerMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AnswerNotifier {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final AnswerMapper answerMapper;

    public void notifyAnswerCount(int count, Answer saved) {
        messagingTemplate.convertAndSend(
                "/topic/test/" + saved.getQuestionnaire().getId() + "/answers/count",
                Map.of("count", count)
        );

        AnswerResponseMessage dto = answerMapper.toMessage(saved);
        messagingTemplate.convertAndSend(
                "/topic/test/" + saved.getQuestionnaire().getId() + "/answers/new",
                dto
        );
    }

    // DTO для отправки данных
    @Setter
    @Getter
    public static class AnswerCountResponse {
        private int count;

        public AnswerCountResponse(int count) {
            this.count = count;
        }

    }
}
