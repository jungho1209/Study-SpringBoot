package com.example.fcm.infrastructure.feign.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleInfoResponse {

    private String accountId;
    private String name;

}
