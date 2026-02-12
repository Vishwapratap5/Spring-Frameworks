package com.guru.springsecurity_learning.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "notices")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    private String noticeSummary;

    private String noticeDetails;

    private LocalDate noticeBeginDate;

    private LocalDate noticeEndDate;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdATime;


    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedATime;

}
