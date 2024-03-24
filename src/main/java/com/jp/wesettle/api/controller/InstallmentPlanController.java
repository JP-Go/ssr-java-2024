package com.jp.wesettle.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jp.wesettle.api.model.InstallmentPlanRepresentation;
import com.jp.wesettle.domain.model.InstallmentPlan;
import com.jp.wesettle.domain.repository.InstallmentPlansRepository;
import com.jp.wesettle.domain.service.InstallmentPlanService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/**
 * InstallmentPlanController
 */
@RestController
@RequestMapping("/installment_plans")
@AllArgsConstructor
public class InstallmentPlanController {

    private final InstallmentPlansRepository repository;
    private final InstallmentPlanService service;

    @GetMapping
    public List<InstallmentPlanRepresentation> findAll(){
        return repository.findAll().stream().map(InstallmentPlanRepresentation::fromEntity).toList();
    }

    @GetMapping("/{planId}")
    public ResponseEntity<InstallmentPlanRepresentation> findAll(@PathVariable Long planId){
        return repository.findById(planId)
                    .map(plan -> ResponseEntity.ok(InstallmentPlanRepresentation.fromEntity(plan)))
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public InstallmentPlanRepresentation createPlan(@Valid @RequestBody InstallmentPlan plan){
        return InstallmentPlanRepresentation.fromEntity(service.create(plan));
    }

}
