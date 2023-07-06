// package com.mechoori.web.api.entity.enums;

// import java.util.Arrays;

// public enum LoginType {
//     GOOGLE(2),
//     NAVER(3),
//     KAKAO(4);

//     private final int loginTypeId;

//     LoginType(int loginTypeId) {
//         this.loginTypeId = loginTypeId;
//     }

//     public int getLoginTypeId() {
//         return loginTypeId;
//     }

//     public static LoginType fromLoginTypeId(int loginTypeId) {
//         for (LoginType loginType : LoginType.values()) {
//                 if (loginType.getLoginTypeId() == loginTypeId) {
//                     return loginType;
//                 }
//             }
//         throw new IllegalArgumentException("Invalid LoginTypeId: " + loginTypeId);
//     }
// }