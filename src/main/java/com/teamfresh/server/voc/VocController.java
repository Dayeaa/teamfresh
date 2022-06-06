package com.teamfresh.server.voc;

import com.teamfresh.server.company.repository.CompanyRepository;
import com.teamfresh.server.voc.dto.VocCreateFormDTO;
import com.teamfresh.server.voc.repositpory.entity.Voc;
import com.teamfresh.server.voc.service.VocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VocController {

    private final VocService vocService;
    private final CompanyRepository companyRepository;

    //생성
    @PostMapping("/api/voc")
    @ResponseBody
    public Voc saveVoc(VocCreateFormDTO vocCreateFormDTO){
        return vocService.createVoc(vocCreateFormDTO);
    }

    //조회
    @GetMapping("/api/vocs")
    public ResponseEntity<List<Voc>> getAllVoc(){
        List<Voc> list = vocService.findAllVocs();
        return ResponseEntity.ok(list);
    }
}
