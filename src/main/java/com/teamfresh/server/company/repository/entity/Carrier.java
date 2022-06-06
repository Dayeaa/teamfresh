package com.teamfresh.server.company.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;

@Entity
@DiscriminatorValue("carrier")
@PrimaryKeyJoinColumn(name = "carrier_id")
@NoArgsConstructor
@Getter
public class Carrier extends Company implements Serializable {
    private String carrierCode;

    @Builder
    public Carrier(Long id, String name, String carrierCode) {
        super(id, name);
        this.carrierCode = carrierCode;
    }
}
