package com.teamfresh.server.voc.repositpory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.teamfresh.server.company.repository.entity.Company;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Penalty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penalty_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Company supplier;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Company receiver;

    private Integer penaltyFee;

    private String memo;

    @Column(name="is_rejected")
    private boolean isRejected;

    private LocalDateTime confirmedAt;

    @JsonBackReference
    @OneToOne(mappedBy = "penalty", fetch = FetchType.LAZY)
    private Voc voc;

    public void setVoc(Voc voc) {
        this.voc = voc;
    }

    public void update(boolean isRejected, LocalDateTime confirmedAt) {
        this.isRejected = isRejected;
        this.confirmedAt = LocalDateTime.now();
    }


}
