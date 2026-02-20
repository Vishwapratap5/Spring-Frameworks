package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<Notice> findByNoticeId(Long id);
}