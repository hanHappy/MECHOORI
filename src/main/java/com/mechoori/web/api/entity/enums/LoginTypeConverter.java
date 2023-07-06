// package com.mechoori.web.api.entity.enums;

// import java.util.Objects;

// import jakarta.persistence.AttributeConverter;
// import jakarta.persistence.Converter;

// @Converter
// public class LoginTypeConverter implements AttributeConverter<LoginType, Integer> {

//     @Override
//     public Integer convertToDatabaseColumn(LoginType attribute) {
//         if (Objects.isNull(attribute)) {
//             return null;
//         }
//         return attribute.getLoginTypeId();
//     }

//     @Override
//     public LoginType convertToEntityAttribute(Integer dbData) {
//         if (Objects.isNull(dbData)) {
//             return null;
//         }
//         return LoginType.fromLoginTypeId(dbData); // fromLoginTypeId는 미리 정의된 함수. 없으면 Exception.
//     }
// }