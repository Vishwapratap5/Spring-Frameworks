package com.guru.springsecurity_learning.DTO.NoticeDTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeResponseDTO {

    private Long noticeId;

    private String noticeSummary;

    private String noticeDetails;

    private LocalDate noticeBeginDate;

    private LocalDate noticeEndDate;
}
