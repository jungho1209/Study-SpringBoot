package com.example.fcm.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryUserListResponse {

    private final List<QueryUserElement> userList;

}
