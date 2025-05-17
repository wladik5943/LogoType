package com.soft_arex.entity;

import com.soft_arex.enums.FieldType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "softarex", name = "field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private FieldType type;
    private boolean required;
    private boolean active;
}
