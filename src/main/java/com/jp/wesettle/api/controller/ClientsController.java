package com.jp.wesettle.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jp.wesettle.api.model.ClientRepresentation;
import com.jp.wesettle.domain.model.Client;
import com.jp.wesettle.domain.repository.ClientRepository;
import com.jp.wesettle.domain.service.ClientRegistryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/**
 * ClientsController
 * 
 */
@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientsController {

    private final ClientRepository clientRepository;
    private final ClientRegistryService clientRegistryService;

    @GetMapping
    public List<ClientRepresentation> listClients(){
        var clients = clientRepository.findAll().stream().map(ClientRepresentation::fromEntity).toList();
        return clients;
    }
    
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientRepresentation> listClients(@PathVariable Long clientId){
        var client = clientRepository.findById(clientId);
        return client.map(c -> ResponseEntity.ok(ClientRepresentation.fromEntity(c))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientRepresentation createClient(@Valid @RequestBody Client client){
        return ClientRepresentation.fromEntity(clientRegistryService.save(client));
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClientRepresentation> updateClientData(@PathVariable Long clientId, @RequestBody Client client){
        if (!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }
        client.setId(clientId);
        client = clientRegistryService.save(client);
        return ResponseEntity.ok(ClientRepresentation.fromEntity(client));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId){
        if (!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }
        clientRegistryService.deleteById(clientId);
        return ResponseEntity.noContent().build();
    }

}
