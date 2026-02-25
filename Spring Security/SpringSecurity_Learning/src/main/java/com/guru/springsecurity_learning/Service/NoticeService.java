package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DTO.ContactDTOs.ContactResponseDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeCreateRequestDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeListResponseDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeResponseDTO;

import java.util.List;

public interface NoticeService {
    NoticeListResponseDTO getAllNotices(int page, int size, String sortBy, String direction);

    NoticeResponseDTO getNoticeById(Long id);

    NoticeResponseDTO deleteNoticeById(Long id);

    NoticeResponseDTO updateNoticeById(Long id, NoticeCreateRequestDTO request);

    NoticeResponseDTO createNotice(NoticeCreateRequestDTO request);
}
