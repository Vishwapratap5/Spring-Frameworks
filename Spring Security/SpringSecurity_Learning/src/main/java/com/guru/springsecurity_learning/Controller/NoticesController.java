package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.ContactDTOs.ContactResponseDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeListResponseDTO;
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
    public ResponseEntity<NoticeListResponseDTO> getAllNotices(  @RequestParam(name="page",defaultValue = "0") int page,
                                                                 @RequestParam(name="size",defaultValue = "10") int size,
                                                                 @RequestParam(name="sortBy",defaultValue = "createdAt") String sortBy,
                                                                 @RequestParam(name="direction",defaultValue = "desc") String direction){
        List<NoticeResponseDTO> notices=noticeService.getAllNotices(page, size, sortBy, direction);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponseDTO> getNoticeById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(noticeService.getNoticeById(id),HttpStatus.OK);
    }


}
