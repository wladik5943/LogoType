package com.soft_arex.entity;

import com.soft_arex.enums.FieldType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(schema = "softarex", name = "field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    @Enumerated(EnumType.STRING)
    private FieldType type;
    private boolean required;
    private boolean active;

    @ElementCollection
    @CollectionTable(name = "field_options",schema = "softarex", joinColumns = @JoinColumn(name = "field_id"))
    @Column(name = "option_value")
    private List<String> options;

    @ManyToOne
    private User user;
}
