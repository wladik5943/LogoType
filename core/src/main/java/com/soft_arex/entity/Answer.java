package com.soft_arex.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "softarex", name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Response response;

    @ManyToOne
    private Field field;

    private String value; // Хранит текст, выбранную опцию и т.д.
}
