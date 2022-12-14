package com.example.studyspringboot.domain.feed.domain;

import com.example.studyspringboot.domain.user.domain.User;
import com.example.studyspringboot.global.entity.BaseTimeIdEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Feed extends BaseTimeIdEntity {

    @NotNull
    @Size(max = 20)
    private String title;

    @NotNull
    @Size(max = 500)
    private String content;

    @NotNull
    private Integer views;

    @NotNull
    private Integer likeCounts;

    @NotNull
    private Integer unLikeCounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Feed(String title, String content, Integer views, Integer likeCounts, Integer unLikeCounts, User user) {
        this.title = title;
        this.content = content;
        this.views = views;
        this.likeCounts = likeCounts;
        this.unLikeCounts = unLikeCounts;
        this.user = user;
    }

    public void modifyFeed(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addViews() {
        this.views += 1;
    }

    public void addLikeCount() {
        this.likeCounts += 1;
    }

    public void minusLikeCount() {
        this.likeCounts -= 1;
    }

    public void addUnLikeCount() {
        this.unLikeCounts += 1;
    }

    public void minusUnLikeCount() {
        this.unLikeCounts -= 1;
    }

}
