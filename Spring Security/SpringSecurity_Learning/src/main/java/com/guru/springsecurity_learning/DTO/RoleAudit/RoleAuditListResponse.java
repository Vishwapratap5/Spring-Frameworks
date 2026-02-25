package com.guru.springsecurity_learning.DTO.RoleAudit;

import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleAuditListResponse {
    List<RoleAuditResponseDTO> roleAuditDTOs;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
