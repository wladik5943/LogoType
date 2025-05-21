package com.soft_arex.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table( name = "response")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    private Questionnaire questionnaire;

    @ManyToOne(optional = true)
    private User submittedBy; // null если аноним

    private LocalDateTime submittedAt;

    @BatchSize(size = 100)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "response_id")
    private List<AnswerField> answers = new ArrayList<>();
}
