package com.jp.wesettle.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jp.wesettle.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{ 

    public List<Client> findByName(String name);
    public List<Client> findByNameContaining(String partialName);
    public Optional<Client> findByEmail(String email);

}
