package com.teamfresh.server.voc.service;

import com.teamfresh.server.company.repository.CompanyRepository;
import com.teamfresh.server.company.repository.entity.Carrier;
import com.teamfresh.server.company.repository.entity.Client;
import com.teamfresh.server.company.repository.entity.Driver;
import com.teamfresh.server.voc.dto.VocCreateFormDTO;
import com.teamfresh.server.voc.repositpory.entity.Voc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class VocServiceTest {

    @Autowired
    private VocService vocService;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void createVoc() {
        // given
        Client client = createClient();
        Driver driver = createDriver();
        Carrier carrier = createCarrier();

        // Case 1
        VocCreateFormDTO vocCreateFormDTO = new VocCreateFormDTO();
        // 귀책 : driver
        vocCreateFormDTO.setAttributableId(driver.getId());
        // 클레임 여부 : client
        vocCreateFormDTO.setRequesterId(client.getId());
        // 공급자 : driver
        vocCreateFormDTO.setSupplierId(driver.getId());
        // 공급받는자 : carrier
        vocCreateFormDTO.setReceiverId(carrier.getId());
        // 공통
        vocCreateFormDTO.setPenaltyFee(10000);
        vocCreateFormDTO.setVocMemo("고객에게 들어온 클레임 건입니다. ~~");
        vocCreateFormDTO.setPenaltyMemo("기사님 ~~ 이유로 패널티 적용됩니다.");

        // when
        Voc voc = vocService.createVoc(vocCreateFormDTO);

        // then
        assertEquals(voc.getMemo(), "고객에게 들어온 클레임 건입니다. ~~");
        assertNotNull(voc.getPenalty());
        assertEquals(voc.getPenalty().getMemo(), "기사님 ~~ 이유로 패널티 적용됩니다.");
        assertEquals(voc.getPenalty().getPenaltyFee(), 10000);
    }

    @Test
    void findAll() {
        // given
        Client client = createClient();
        Driver driver = createDriver();
        Carrier carrier = createCarrier();

        // Case 1
        VocCreateFormDTO vocCreateFormDTO = new VocCreateFormDTO();
        // 귀책 : driver
        vocCreateFormDTO.setAttributableId(driver.getId());
        // 클레임 여부 : client
        vocCreateFormDTO.setRequesterId(client.getId());
        // 공급자 : driver
        vocCreateFormDTO.setSupplierId(driver.getId());
        // 공급받는자 : carrier
        vocCreateFormDTO.setReceiverId(carrier.getId());
        // 공통
        vocCreateFormDTO.setPenaltyFee(10000);
        vocCreateFormDTO.setVocMemo("고객에게 들어온 클레임 건입니다. ~~");
        vocCreateFormDTO.setPenaltyMemo("기사님 ~~ 이유로 패널티 적용됩니다.");
        Voc voc = vocService.createVoc(vocCreateFormDTO);

        // when
        List<Voc> findList = vocService.findAllVocs();

        // then
        assertFalse(findList.isEmpty());
    }

    private Client createClient() {
        Client client = Client.builder()
                .name("화주")
                .clientCode("A0011")
                .phone("01012341234")
                .build();
        companyRepository.save(client);
        return client;
    }
    private Carrier createCarrier() {
        Carrier carrier = Carrier.builder()
                .name("운송사")
                .carrierCode("C0011")
                .build();
        companyRepository.save(carrier);
        return carrier;
    }

    private Driver createDriver() {
        Driver driver =  Driver.builder()
                .name("김갑순")
                .driverCode("D00111")
                .phone("01012341234")
                .build();
        companyRepository.save(driver);
        return driver;
    }
}