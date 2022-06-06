package com.teamfresh.server.voc.constant;

public enum VocStatus {
    REQUEST, // 승인요청
    WAITING, // 기사 승인 프로세스
    REJECT, // 이의 제기
    COMPENSATION_WAITING, // 승인 완료
    COMPLETE // 배상 완료
}
