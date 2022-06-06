package com.teamfresh.server.voc;

import com.teamfresh.server.voc.dto.PenaltyUpdateFormDTO;
import com.teamfresh.server.voc.service.VocService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/penalties")
@RequiredArgsConstructor
public class PenaltyController {
    private final VocService vocService;

    @PutMapping("/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PenaltyUpdateFormDTO penaltyUpdateFormDTO){
        return vocService.updatePenaltyInfo(id,penaltyUpdateFormDTO);
    }




}
