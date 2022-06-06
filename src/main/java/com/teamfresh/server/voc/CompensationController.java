package com.teamfresh.server.voc;

import com.teamfresh.server.voc.repositpory.entity.Compensation;
import com.teamfresh.server.voc.service.VocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/compensations")
@RequiredArgsConstructor
public class CompensationController {
    private VocService vocService;

    //조회
    @GetMapping
    public ResponseEntity<List<Compensation>> getAllVoc(){
        List<Compensation> list = vocService.findAllCompensations();
        return ResponseEntity.ok(list);
    }

}
