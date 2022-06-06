package com.teamfresh.server.company.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;

@Entity
@DiscriminatorValue("driver")
@PrimaryKeyJoinColumn(name = "driver_id")
@NoArgsConstructor
@Getter
public class Driver extends Company implements Serializable {
    private String driverCode;
    private String phone;

    @Builder
    public Driver(Long id, String name, String driverCode, String phone) {
        super(id, name);
        this.driverCode = driverCode;
        this.phone = phone;
    }
}
