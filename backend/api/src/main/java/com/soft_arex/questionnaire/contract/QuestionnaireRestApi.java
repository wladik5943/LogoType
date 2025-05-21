package com.soft_arex.questionnaire.contract;

import com.soft_arex.questionnaire.model.QuestionnaireRequestDTO;
import com.soft_arex.questionnaire.model.QuestionnaireResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/test")
public interface QuestionnaireRestApi {


    @PatchMapping("/{id}/status")
    QuestionnaireResponseDTO updateStatus(@PathVariable("id") Long id);

    @GetMapping("/all")
    List<QuestionnaireResponseDTO> getAll();

    @GetMapping("/mine")
    List<QuestionnaireResponseDTO> getMine();

    @GetMapping("/{id}")
    QuestionnaireResponseDTO getById(@PathVariable Long id);

    @PostMapping
    QuestionnaireResponseDTO create(@RequestBody QuestionnaireRequestDTO dto);

    @PutMapping("/{id}")
    QuestionnaireResponseDTO update(@PathVariable Long id, @RequestBody QuestionnaireRequestDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
