package com.teamfresh.server.voc.service;

import com.teamfresh.server.company.repository.CompanyRepository;
import com.teamfresh.server.voc.constant.CompensationStatus;
import com.teamfresh.server.voc.dto.PenaltyUpdateFormDTO;
import com.teamfresh.server.voc.dto.VocCreateFormDTO;
import com.teamfresh.server.voc.repositpory.CompensationRepository;
import com.teamfresh.server.voc.repositpory.PenaltyRepository;
import com.teamfresh.server.voc.repositpory.VocRepository;
import com.teamfresh.server.voc.repositpory.entity.Compensation;
import com.teamfresh.server.voc.repositpory.entity.Penalty;
import com.teamfresh.server.voc.repositpory.entity.Voc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VocService {

    private final VocRepository vocRepository;
    private final CompanyRepository companyRepository;
    private final PenaltyRepository penaltyRepository;
    private final CompensationRepository compensationRepository;

    @Transactional
    public Voc createVoc(VocCreateFormDTO vocCreateFormDTO) {
        // 1. voc 생성 (voc 등록)
        Voc voc = vocRepository.save(vocCreateFormDTO.toVocEntity(companyRepository));

        // 2. penalty 생성
        Penalty penalty = vocCreateFormDTO.toPenaltyEntity(companyRepository);
        penaltyRepository.save(penalty);
        voc.registerPenalty(penalty);

        return voc;
    }

    public List<Voc> findAllVocs() {
        return vocRepository.findAll();
    }

    // 1. create 보상
    @Transactional
    public Compensation createCompensation(Long vocId) {
        // 1. voc 조회
        Optional<Voc> voc = vocRepository.findById(vocId);
        if (voc.isEmpty()) {
            throw new RuntimeException("voc를 찾을 수 없습니다.");
        }

        // 2. compensation 객체 생성 후 저장 (배상정보 등록)
        Compensation compensation = Compensation.builder()
                .voc(voc.get())
                .compensationStatus(CompensationStatus.WAITING)
                .build();
        return compensationRepository.save(compensation);
    }

    public List<Compensation> findAllCompensations() {
        return compensationRepository.findAll();
    }


    @Transactional
    public Long updatePenaltyInfo(Long id, PenaltyUpdateFormDTO penaltyUpdateDTO) {
        Penalty penalty = penaltyRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        penalty.update(penaltyUpdateDTO.isRejected(),penaltyUpdateDTO.getConfirmedAt());
        return id;
    }
}
