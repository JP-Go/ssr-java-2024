package com.jp.wesettle.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.jp.wesettle.domain.validation.ValidationGroups;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * InstallmentPlan
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "installment_plans")
@Entity
public class InstallmentPlan {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
    private Client client;

    @Column(name = "description")
    @Size(max = 60)
    private String description;

    @Column(name = "end_value")
    @Positive
    private BigDecimal value;

    @Column(name = "installments")
    @NotNull(message = "Number of installments must not be null")
    @Max(value = 96)
    private Integer installments;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
