package com.soft_arex.entity;

import com.soft_arex.enums.FieldType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Data
@Entity
@Table( name = "field")
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
    @BatchSize(size = 100)
    @CollectionTable(name = "field_options", joinColumns = @JoinColumn(name = "field_id"))
    @Column(name = "option_value")
    private List<String> options;

    @ManyToOne
    private User user;
}
