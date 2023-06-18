package com.mechoori.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoMember {

    private String kakaoId;
    private String username;
    private String email;
    private int genderId;

}
