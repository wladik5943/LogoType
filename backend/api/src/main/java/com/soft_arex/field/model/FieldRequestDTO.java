package com.soft_arex.field.model;

import com.soft_arex.enums.FieldType;
import lombok.Data;

import java.util.List;

@Data
public class FieldRequestDTO {
    private String label;
    private FieldType type;
    private boolean required;
    private boolean active;
    private List<String> options;
}