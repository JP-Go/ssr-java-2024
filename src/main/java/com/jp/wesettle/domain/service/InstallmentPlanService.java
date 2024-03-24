package com.jp.wesettle.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.wesettle.domain.exception.DomainRuleException;
import com.jp.wesettle.domain.model.Client;
import com.jp.wesettle.domain.model.InstallmentPlan;
import com.jp.wesettle.domain.repository.InstallmentPlansRepository;

import lombok.AllArgsConstructor;

/**
 * InstallmentPlanService
 */
@AllArgsConstructor
@Service
public class InstallmentPlanService {

    private final InstallmentPlansRepository repository;
    private final ClientRegistryService clientRegistry;

    @Transactional
    public InstallmentPlan create(InstallmentPlan plan){
        if (plan.getId() != null){
            throw new DomainRuleException("Plano de pagamento não deve possuir um id");
        }
        Client client = clientRegistry.findById(plan.getClient().getId());
        plan.setCreatedAt(OffsetDateTime.now());
        plan.setClient(client);
        return repository.save(plan);
    }
    
}
