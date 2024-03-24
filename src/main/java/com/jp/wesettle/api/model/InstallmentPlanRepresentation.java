package com.jp.wesettle.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.jp.wesettle.domain.model.InstallmentPlan;

import lombok.Getter;
import lombok.Setter;

/**
 * InstallmentPlanRepresentation
 */
@Getter
@Setter
public class InstallmentPlanRepresentation {

    private Long id;
    private String clientName;
    private String description;
    private BigDecimal endValue;
    private Integer installments;
    private OffsetDateTime creationDate;

    public static InstallmentPlanRepresentation fromEntity(InstallmentPlan plan){
        var repr = new InstallmentPlanRepresentation();
        repr.setClientName(plan.getClient().getName());
        repr.setId(plan.getId());
        repr.setEndValue(plan.getValue());
        repr.setInstallments(plan.getInstallments());
        repr.setCreationDate(plan.getCreatedAt());
        repr.setDescription(plan.getDescription());
        return repr;
    }
}
