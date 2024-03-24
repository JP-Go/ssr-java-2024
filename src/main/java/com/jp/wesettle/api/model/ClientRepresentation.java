package com.jp.wesettle.api.model;

import com.jp.wesettle.domain.model.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * ClientRepresentation
 */
@Getter
@Setter
public class ClientRepresentation {

    private Long id;
    private String name;
    private String phone;
    private String email;


    public static ClientRepresentation fromEntity(Client client){
        var repr = new ClientRepresentation();
        repr.setId(client.getId());
        repr.setName(client.getName());
        repr.setPhone(client.getPhone());
        repr.setEmail(client.getEmail());
        return repr;
    }

}
