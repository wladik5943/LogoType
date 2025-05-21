package com.soft_arex.repository;

import com.soft_arex.entity.Field;
import com.soft_arex.entity.Questionnaire;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {



    @EntityGraph(attributePaths = {"fields"})
    @Query("SELECT q FROM Questionnaire q WHERE q.id = :id")
    Optional<Questionnaire> findWithFieldsById(@Param("id") Long id);

    @NonNull
    @EntityGraph(attributePaths = {"fields", "fields.user"})
    List<Questionnaire> findAll();

    List<Questionnaire> findByUserId(Long id);
}
