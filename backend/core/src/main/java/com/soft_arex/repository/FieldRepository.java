package com.soft_arex.repository;

import com.soft_arex.entity.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    @Query("SELECT DISTINCT f FROM Field f LEFT JOIN FETCH f.options WHERE f.id IN :ids")
    List<Field> findAllWithOptionsByIds(@Param("ids") List<Long> ids);

    Page<Field> findByUserIdOrderById(Long userId, Pageable pageable);

}
