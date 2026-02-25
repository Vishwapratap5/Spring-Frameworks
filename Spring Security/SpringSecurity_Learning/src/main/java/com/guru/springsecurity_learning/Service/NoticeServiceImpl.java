package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.NoticeRepository;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeCreateRequestDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeListResponseDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeResponseDTO;
import com.guru.springsecurity_learning.Exception.ResourceNotFoundException;
import com.guru.springsecurity_learning.Model.Notice;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository  noticeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public NoticeListResponseDTO getAllNotices(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Notice> notices= noticeRepository.findAll(pageable);

        if(notices.isEmpty()){
            throw new ResourceNotFoundException("notice not found");
        }
        List<NoticeResponseDTO> noticeResponseDTOS=notices.getContent().stream().map((notice)->modelMapper.map(notice,NoticeResponseDTO.class)).toList();
        NoticeListResponseDTO noticeListResponseDTO = new NoticeListResponseDTO();
        noticeListResponseDTO.setNoticeList(noticeResponseDTOS);
        noticeListResponseDTO.setTotalPages(notices.getTotalPages());
        noticeListResponseDTO.setTotalElements(notices.getTotalElements());
        noticeListResponseDTO.setPageNumber(notices.getNumber());
        noticeListResponseDTO.setPageSize(notices.getSize());
        noticeListResponseDTO.setLast(notices.isLast());
        return noticeListResponseDTO;


    }

    @Override
    public NoticeResponseDTO getNoticeById(Long id) {
        Notice notice=noticeRepository.findByNoticeId(id).orElseThrow(()->new ResourceNotFoundException("notice not found"));
        return modelMapper.map(notice,NoticeResponseDTO.class);
    }

    @Override
    public NoticeResponseDTO deleteNoticeById(Long id) {
        Notice notice=noticeRepository.findByNoticeId(id).orElseThrow(()->new ResourceNotFoundException("notice not found"));
        noticeRepository.delete(notice);
        return modelMapper.map(notice,NoticeResponseDTO.class);
    }

    @Override
    public NoticeResponseDTO updateNoticeById(Long id, NoticeCreateRequestDTO request) {
        Notice notice=noticeRepository.findByNoticeId(id).orElseThrow(()->new ResourceNotFoundException("notice not found"));
        notice.setNoticeDetails(request.getNoticeDetails());
        notice.setNoticeSummary(request.getNoticeSummary());
        notice.setNoticeEndDate(request.getNoticeEndDate());
        notice.setNoticeBeginDate(request.getNoticeBeginDate());
        Notice savedNotice=noticeRepository.save(notice);
        return modelMapper.map(savedNotice,NoticeResponseDTO.class);

    }

    @Override
    public NoticeResponseDTO createNotice(NoticeCreateRequestDTO request) {
        Notice notice=modelMapper.map(request,Notice.class);
        return modelMapper.map(noticeRepository.save(notice),NoticeResponseDTO.class);
    }

}
