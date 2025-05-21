package com.soft_arex.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "answer")
public class AnswerField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Field field;

    @Column(length = 2048)
    private String value;  // Хранит текст, выбранную опцию и т.д.
}
