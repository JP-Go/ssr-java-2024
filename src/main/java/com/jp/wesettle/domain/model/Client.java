package com.jp.wesettle.domain.model;

import com.jp.wesettle.domain.validation.ValidationGroups;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Client
 */
@Getter
@Setter
@Entity
@Table(name = "clients")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationGroups.ClientId.class)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "client_name")
    @NotBlank
    @Size(max = 80)
    private String name;

    @Column(name = "client_email")
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    
    @Column(name = "client_phone")
    @NotBlank
    @Size(max = 255)
    private String phone;
    
}
