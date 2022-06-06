package com.teamfresh.server.voc.repositpory;

import com.teamfresh.server.voc.repositpory.entity.Voc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VocRepository extends JpaRepository<Voc,Long> {

    Optional<Voc> findByPenaltyId(Long penaltyId);
}
