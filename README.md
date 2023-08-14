# :fork_and_knife: MECHOORI
내 손 안의 메뉴 추천 리스트

> ![java](https://img.shields.io/badge/Java-17-DEB887?style=flat)
> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white"/>
> <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat&logo=SpringSecurity&logoColor=white"/>
> <img src="https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=MariaDB&logoColor=white"> 
> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black">
> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white"> 
> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white">  
> <img src="https://img.shields.io/badge/Git-F05032?style=flat-square&logo=Git&logoColor=white">
> <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white">
> <img src="https://img.shields.io/badge/VisualStudioCode-007ACC?style=flat-square&logo=VisualStudioCode&logoColor=white">
> <img src="https://img.shields.io/badge/Figma-F24E1E?style=flat-square&logo=Figma&logoColor=white">
> ![Notion](https://img.shields.io/badge/Notion-606060.svg?style=flat&logo=notion&logoColor=white)   

<br>

# 목차
[1. 프로젝트 소개](#1-프로젝트-소개) <br>
[2. 제작 기간 & 참여 인원](#2-제작-기간-및-참여-인원) <br>
[3. 사용 기술](#3-사용-기술) <br>
[4. ERD 설계](#4-ERD-설계) <br>
[5. 핵심 기능 & 실행 화면](#5-핵심-기능-및-실행-화면) <br>
[6. 이슈](#6-핵심-트러블-슈팅)

<br>

## 1. 프로젝트 소개
- 가성비를 통해 식당 리뷰의 새로운 기준을 세웁니다.
- 다양한 식당의 메뉴들을 가성비와 함께 비교하여 가성비 좋은 식당을 손쉽게 찾을 수 있는 서비스를 제공합니다.

<br>

## 2. 제작 기간 및 참여 인원
- 2023.04.17 ~ 2023.07.19 (3개월)
- 5명 (김현철, 박현준, 최서우, 한상민, 허세영)

<br>

## 3. 사용 기술

#### `Back-end`

- Java (JDK 17)
- Spring
- Spring Boot 3.1.0
- MyBatis 3.0.2

#### `Front-end`

- JavaScript
- HTML5
- CSS3

#### `Build Tool`

- Maven 4.0

#### `DataBase`

- MariaDB 10.6.12

#### `IDE & Collaboration`

- VSCode
- Eclipse
- MySQL Workbench
- IntelliJ

#### `기타 도구`

- Git, GitHub
- Figma
- Google Spread Sheet
- draw.io
- Notion
- Discord

<br>

## 4. ERD 설계

<img width="110%" src="https://i.imgur.com/zeV43g9.png"/>

<br>

## 5. 핵심 기능 및 실행 화면

[5-1. 식당 조회 및 평가 시스템](#5-1-식당-조회-및-평가-시스템) <br>
- 식당 목록 및 상세 조회
- 식당 가성비 평가
- 지도 -> 식당 조회회

[5-2. 데이터 분석 시스템](#5-2-데이터-분석-시스템) <br>
- 평가 통계 데이터 조회
- 식당 랭킹 조회

[5-3. 사용자 관리 시스템](#5-3-사용자-관리-시스템) <br>
- 로그인
- 회원가입
- 회원 정보 변경
- 비밀번호 찾기

[5-4. 회원 개인화 시스템](#5-4-회원-개인화-시스템) <br>
- 찜한 목록 조회
- 평가 목록 조회

<br>

### 5-1. 식당 조회 및 평가 시스템

#### 5-1-1. 식당 목록 및 상세 조회

<table>
<thead>
  <tr>
    <th align="center">식당 목록 페이지 (카테고리 검색)</th>
    <th align="center">식당 목록 페이지 (키워드 검색)</th>
  </tr>
</thead>
<tbody>
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/203f1248-e89a-405e-ad5c-008091016997"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/203f1248-e89a-405e-ad5c-008091016997" width="50%" style="max-width: 100%"></a></td>
  <td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/adbbf02d-4166-4b54-bf94-6e60807ddb15"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/adbbf02d-4166-4b54-bf94-6e60807ddb15" width="50%" style="max-width: 100%"></a></td>
</tbody>  
</table>
<br>

<table>
<thead>
  <tr>
    <th align="center">식당 상세 페이지</th>
  </tr>
</thead>
<tbody>
<tbody>
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/53349132-0f6d-4b8d-b1a6-cb428265f9ab"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/53349132-0f6d-4b8d-b1a6-cb428265f9ab" width="50%" style="max-width: 100%"></a></td>
</tbody>  
</table>

#### 5-1-3. 지도 -> 식당 조회

<table>
<thead>
  <tr>
    <th align="center">지도 페이지 (내 위치)</th>
    <th align="center">지도 페이지 (식당 조회)</th>
  </tr>
</thead>
<tbody>
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/9a815837-0a92-4a28-9121-6fe60285e2fa"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/9a815837-0a92-4a28-9121-6fe60285e2fa" width="50%" style="max-width: 100%"></a></td>
  <td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/cbfc807a-247e-4f5c-bc61-cd2127b9302e"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/cbfc807a-247e-4f5c-bc61-cd2127b9302e" width="50%" style="max-width: 100%"></a></td>
</tbody>  
</table>


### 5-2. 데이터 분석 시스템

#### 5-2-1. 평가 통계 데이터 조회

<table>
<thead>
  <tr>
    <th align="center">나의 통계 데이터 페이지</th>
  </tr>
</thead>
<tbody>
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/118332255/3a05f54b-31ef-49fe-bcff-750664e9114a"><img src="https://github.com/hanHappy/MECHOORI/assets/118332255/3a05f54b-31ef-49fe-bcff-750664e9114a" width="50%" style="max-width: 100%"></a></td>
</tbody>  
</table>

#### 5-2-2. 식당 랭킹 조회

<table>
<thead>
  <tr>
    <th align="center">랭킹 페이지</th>
    <th align="center">랭킹 페이지 (카테고리별)</th>
  </tr>
</thead>
<tbody>
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/b0e11c62-fac0-4413-908b-24cc5c686d54"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/b0e11c62-fac0-4413-908b-24cc5c686d54" width="50%" style="max-width: 100%"></a></td>
  <td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/11057fa9-8bdc-4ddd-9488-e8e04eae2668"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/11057fa9-8bdc-4ddd-9488-e8e04eae2668" width="50%" style="max-width: 100%"></a></td>
</tbody>   
</table>

### 5-3. 사용자 관리 시스템

#### 5-3-1. 로그인

<table>
<thead>
<tr>
<th align="center">일반 로그인 페이지</th>
<th align="center">소셜 로그인 페이지 (구글)</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/9bffdaec-0f0b-4829-8833-d316f03c16e0"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/9bffdaec-0f0b-4829-8833-d316f03c16e0" width="50%" style="max-width: 100%;"></a></td>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/ff11d0fb-b26b-4a0b-80a6-f49f56081e33"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/ff11d0fb-b26b-4a0b-80a6-f49f56081e33" width="50%" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>

#### 5-3-2. 회원가입

<table>
<thead>
<tr>
<th align="center">회원가입 페이지</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/66f6f995-53c7-48d5-8969-b23f365d7cba"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/66f6f995-53c7-48d5-8969-b23f365d7cba" width="50%" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>

#### 5-3-3. 회원 정보 변경, 비밀번호 찾기

<table>
<thead>
<tr>
<th align="center">회원 정보 수정 페이지</th>
<th align="center">비밀번호 찾기 페이지</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/36093f68-9267-4f99-ba47-8751ff0b55dd"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/36093f68-9267-4f99-ba47-8751ff0b55dd" width="50%" style="max-width: 100%;"></a></td>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/94fbdb6e-5bc2-4349-a96d-90ac8f35572d"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/94fbdb6e-5bc2-4349-a96d-90ac8f35572d" width="50%" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>


### 5-4. 회원 개인화 시스템

마이페이지 - 찜&평가 목록 조회

<table>
<thead>
<tr>
<th align="center">찜 목록 페이지</th>
<th align="center">평가 목록 페이지</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center" width="468px" valign="top"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/c0e6ec38-7110-49e3-aafb-c2a134753c9f"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/c0e6ec38-7110-49e3-aafb-c2a134753c9f" width="50%" style="max-width: 100%;"></a></td>
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/cce97df1-4ef0-49cf-917e-f1064dd14b84"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/cce97df1-4ef0-49cf-917e-f1064dd14b84" height="100%" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>
