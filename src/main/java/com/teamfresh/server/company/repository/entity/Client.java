package com.teamfresh.server.company.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;

@Entity
@DiscriminatorValue("client")
@PrimaryKeyJoinColumn(name = "client_id")
@NoArgsConstructor
@Getter
public class Client extends Company implements Serializable {
    private String clientCode;
    private String phone;

    @Builder
    public Client(Long id, String name, String clientCode, String phone) {
        super(id, name);
        this.clientCode = clientCode;
        this.phone = phone;
    }
}
