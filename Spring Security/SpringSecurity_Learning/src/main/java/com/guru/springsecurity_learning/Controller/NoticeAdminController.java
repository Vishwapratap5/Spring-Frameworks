package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeCreateRequestDTO;
import com.guru.springsecurity_learning.DTO.NoticeDTOs.NoticeResponseDTO;
import com.guru.springsecurity_learning.Service.NoticeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/notice")
@PreAuthorize("hasRole('ADMIN')")
public class NoticeAdminController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public ResponseEntity<NoticeResponseDTO> createNotice(@Valid  @RequestBody NoticeCreateRequestDTO request){
        return new ResponseEntity<>(noticeService.createNotice(request), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeResponseDTO> updateContactById(@Valid @PathVariable Long id,@Valid  @RequestBody NoticeCreateRequestDTO request) {
        return new ResponseEntity<>(noticeService.updateNoticeById(id,request), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<NoticeResponseDTO> deleteContactById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(noticeService.deleteNoticeById(id), HttpStatus.OK);
    }
}
