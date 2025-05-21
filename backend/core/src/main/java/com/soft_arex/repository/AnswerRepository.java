package com.soft_arex.repository;

import com.soft_arex.entity.Answer;
import com.soft_arex.entity.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {


    Page<Answer> findByQuestionnaireId(Long testId, Pageable pageable);
    int countByQuestionnaireId(Long questionnaireId);
}
