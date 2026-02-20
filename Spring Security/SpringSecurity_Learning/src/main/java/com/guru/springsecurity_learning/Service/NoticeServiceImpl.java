package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.DAO.NoticeRepository;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeCreateRequestDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeResponseDTO;
import com.guru.springsecurity_learning.Exception.ResourceNotFoundException;
import com.guru.springsecurity_learning.Model.Notice;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository  noticeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NoticeResponseDTO> getAllNotices() {
        List<NoticeResponseDTO> notices= noticeRepository.findAll().stream().map(notice->modelMapper.map(notice,NoticeResponseDTO.class)).toList();
        if(notices.isEmpty()){
            throw new ResourceNotFoundException("notice not found");
        }
        return notices;
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
