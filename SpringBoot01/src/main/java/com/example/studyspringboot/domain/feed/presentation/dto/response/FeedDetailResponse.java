package com.example.studyspringboot.domain.feed.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedDetailResponse {

    private final Integer feedId;
    private final String title;
    private final String content;
    private final Integer views;
    private final Integer likeCounts;
    private final Integer unLikeCounts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;

}
