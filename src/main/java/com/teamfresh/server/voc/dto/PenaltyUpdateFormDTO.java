package com.teamfresh.server.voc.dto;

import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class PenaltyUpdateFormDTO {
    private boolean rejected;
    private LocalDateTime confirmedAt;

}
