package com.teamfresh.server.voc.repositpory.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.teamfresh.server.company.repository.entity.Company;
import com.teamfresh.server.voc.constant.VocStatus;
import com.teamfresh.server.voc.dto.VocResponseDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Voc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voc_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attributable_id")
    private Company attributableCompany;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private Company requesterCompany;

    private String memo;

    @Enumerated
    private VocStatus status;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "penalty_id")
    private Penalty penalty;

    public void registerPenalty(Penalty penalty) {
        this.penalty = penalty;
        penalty.setVoc(this);
    }

    public VocResponseDTO of() {
        VocResponseDTO vocResponseDTO= new VocResponseDTO();
        vocResponseDTO.setPenalty(this.penalty);
        return vocResponseDTO;
    }
}