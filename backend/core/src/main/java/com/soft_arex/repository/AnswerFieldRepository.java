package com.soft_arex.repository;

import com.soft_arex.entity.AnswerField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerFieldRepository extends CrudRepository<AnswerField, Long> {
}
