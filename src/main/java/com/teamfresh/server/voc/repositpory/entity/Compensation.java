package com.teamfresh.server.voc.repositpory.entity;

import com.teamfresh.server.voc.constant.CompensationStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Compensation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compensation_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "voc_id")
    private Voc voc;

    @Enumerated
    private CompensationStatus compensationStatus = CompensationStatus.WAITING;
}
