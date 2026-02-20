package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.ContactDTOs.ContactResponseDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeResponseDTO;
import com.guru.springsecurity_learning.Service.ContactServiceImpl;
import com.guru.springsecurity_learning.Service.NoticeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/notices")
public class NoticesController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/all")
    public ResponseEntity< List<NoticeResponseDTO>>getAllNotices(){
        List<NoticeResponseDTO> notices=noticeService.getAllNotices();
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponseDTO> getNoticeById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(noticeService.getNoticeById(id),HttpStatus.OK);
    }


}
