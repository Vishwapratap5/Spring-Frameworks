package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.ContactDTOs.ContactResponseDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeCreateRequestDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeResponseDTO;

import java.util.List;

public interface NoticeService {
    List<NoticeResponseDTO> getAllNotices();

    NoticeResponseDTO getNoticeById(Long id);

    NoticeResponseDTO deleteNoticeById(Long id);

    NoticeResponseDTO updateNoticeById(Long id, NoticeCreateRequestDTO request);

    NoticeResponseDTO createNotice(NoticeCreateRequestDTO request);
}
