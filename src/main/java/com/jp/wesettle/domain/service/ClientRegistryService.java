package com.jp.wesettle.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.wesettle.domain.exception.DomainRuleException;
import com.jp.wesettle.domain.model.Client;
import com.jp.wesettle.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

/**
 * ClientRegisterService
 */
@Service
@AllArgsConstructor
public class ClientRegistryService {

    private final ClientRepository clientRepository;

    public Client findById(Long clientId){
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new DomainRuleException("Cliente não encontrado"));
    }

    @Transactional
    public Client save(Client client){
        boolean emailTaken = clientRepository.findByEmail(client.getEmail())
            .filter(c -> !c.equals(client)).isPresent();

        if (emailTaken){
            throw new DomainRuleException("Email já utilizado");
        }
        return clientRepository.save(client);
    }

    @Transactional
    public void deleteById(Long clientId){
        clientRepository.deleteById(clientId);
    }
    
}
