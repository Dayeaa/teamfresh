package com.teamfresh.server.voc.dto;

import com.teamfresh.server.company.repository.CompanyRepository;
import com.teamfresh.server.company.repository.entity.Company;
import com.teamfresh.server.voc.constant.VocStatus;
import com.teamfresh.server.voc.repositpory.entity.Penalty;
import com.teamfresh.server.voc.repositpory.entity.Voc;
import lombok.Data;

import java.util.Optional;

@Data
public class VocCreateFormDTO {
    // voc
    private Long attributableId; //귀책대상
    private Long requesterId;  //배상요청대상
    private String vocMemo; //voc메모

    // penalty
    private Integer penaltyFee; //패널티금액
    private String penaltyMemo; //패널티내용
    private Long supplierId; //배상공급자
    private Long receiverId; //배상수령자


    public Voc toVocEntity(CompanyRepository companyRepository) throws RuntimeException {
        Optional<Company> attributableCompany = companyRepository.findById(attributableId);
        Optional<Company> requesterCompany = companyRepository.findById(requesterId);

        if (attributableCompany.isEmpty() || requesterCompany.isEmpty()) {
            throw new RuntimeException("cannot find resources");
        }

        return Voc.builder()
                .attributableCompany(attributableCompany.get())
                .requesterCompany(requesterCompany.get())
                .memo(vocMemo)
                .status(VocStatus.REQUEST)
                .build();
    }

    public Penalty toPenaltyEntity(CompanyRepository companyRepository) throws RuntimeException {
        Optional<Company> supplier = companyRepository.findById(supplierId);
        Optional<Company> receiver = companyRepository.findById(receiverId);

        if (supplier.isEmpty() || receiver.isEmpty()) {
            throw new RuntimeException("cannot find resources");
        }

        return Penalty.builder()
                .confirmedAt(null)
                .isRejected(false)
                .penaltyFee(penaltyFee)
                .memo(penaltyMemo)
                .supplier(supplier.get())
                .receiver(receiver.get())
                .build();
    }
}
