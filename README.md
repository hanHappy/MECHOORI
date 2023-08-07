# :fork_and_knife: MECHOORI
내 손 안의 메뉴 추천 리스트

> ![java](https://img.shields.io/badge/Java-17-DEB887?style=flat)
> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white"/>
> <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat&logo=SpringSecurity&logoColor=white"/>
> <img src="https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=MariaDB&logoColor=white"> 
> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
> <img src="https://img.shields.io/badge/Vue.js-4FC08D?style=flat-square&logo=Vue.js&logoColor=white">
> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black">
> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white"> 
> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white">  
> <img src="https://img.shields.io/badge/Vite-646CFF?style=flat-square&logo=Vite&logoColor=white">
> <img src="https://img.shields.io/badge/Git-F05032?style=flat-square&logo=Git&logoColor=white">
> <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white">
> <img src="https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=Postman&logoColor=white">
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
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/106819402/f3313042-b885-4509-9c6c-526982468fe8"><img src="https://github.com/hanHappy/MECHOORI/assets/106819402/f3313042-b885-4509-9c6c-526982468fe8" width="50%" style="max-width: 100%"></a></td>
  <td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/106819402/3b2fa1a5-784f-4319-961e-36f450d76957"><img src="https://github.com/hanHappy/MECHOORI/assets/106819402/3b2fa1a5-784f-4319-961e-36f450d76957" width="50%" style="max-width: 100%"></a></td>
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
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/3378eaa4-ab05-4b1b-884f-052dfbbf4cea"><img src="htt지</th>
  </tr>
</thead>
<tbody>
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/106819402/774d94be-a57a-4e0a-9c75-2d52b6960c20"><img src="https://github.com/hanHappy/MECHOORI/assets/106819402/774d94be-a57a-4e0a-9c75-2d52b6960c20" width="50%" style="max-width: 100%"></a></td>
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
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/125805728/23ab78a0-c014-42e2-816b-ae1b694efc90"><img src="https://github.com/hanHappy/MECHOORI/assets/125805728/23ab78a0-c014-42e2-816b-ae1b694efc90" width="50%" style="max-width: 100%"></a></td>
  <td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/125805728/0ddf6855-3bec-402c-bc5e-6cb2c33925bd"><img src="https://github.com/hanHappy/MECHOORI/assets/125805728/0ddf6855-3bec-402c-bc5e-6cb2c33925bd" width="50%" style="max-width: 100%"></a></td>
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
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/125805728/4960e2f1-5218-4b6a-8a7a-10258997ac29"><img src="https://github.com/hanHappy/MECHOORI/assets/125805728/4960e2f1-5218-4b6a-8a7a-10258997ac29" width="50%" style="max-width: 100%"></a></td>
  <td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/125805728/5ae29270-7fcf-4682-b261-b8cc70ffc618"><img src="https://github.com/hanHappy/MECHOORI/assets/125805728/5ae29270-7fcf-4682-b261-b8cc70ffc618" width="50%" style="max-width: 100%"></a></td>
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
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/124006794/3146ca3a-233a-4bef-b061-f0989cb18152"><img src="https://github.com/hanHappy/MECHOORI/assets/124006794/3146ca3a-233a-4bef-b061-f0989cb18152" width="50%" style="max-width: 100%;"></a></td>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/124006794/bdc76575-1843-4fc4-999e-3f76b853034e"><img src="https://github.com/hanHappy/MECHOORI/assets/124006794/bdc76575-1843-4fc4-999e-3f76b853034e" width="50%" style="max-width: 100%;"></a></td>
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
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/124006794/186a2cd9-f70b-4584-9212-e8063d697873"><img src="https://github.com/hanHappy/MECHOORI/assets/124006794/186a2cd9-f70b-4584-9212-e8063d697873" width="50%" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>

#### 5-3-3. 회원 정보 변경, 비밀번호 찾기

<table>
<thead>
<tr>
<th align="center">회원 정보 수정 페이지</th>
<th align="center">비밀번호 찾기 페이지지</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/126304637/30f3dc5a-7f10-4a45-8975-9bb6ee399d91"><img src="https://github.com/hanHappy/MECHOORI/assets/126304637/30f3dc5a-7f10-4a45-8975-9bb6ee399d91" width="50%" style="max-width: 100%;"></a></td>
<td align="center" valign="top"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/124006794/b8642655-418f-469a-a2e3-141ad3efa35b"><img src="https://github.com/hanHappy/MECHOORI/assets/124006794/b8642655-418f-469a-a2e3-141ad3efa35b" width="50%" style="max-width: 100%;"></a></td>
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
<td align="center" width="468px" valign="top"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/118332255/2e884bf2-78eb-4f41-9ece-c10775450621"><img src="https://github.com/hanHappy/MECHOORI/assets/124006794/b8642655-418f-469a-a2e3-141ad3efa35b" width="50%" style="max-width: 100%;"></a></td>
<td align="center" width="468px"><a target="_blank" rel="noopener noreferrer" href="https://github.com/hanHappy/MECHOORI/assets/118332255/e602e4fb-ae69-4219-96e4-792c704d85e1"><img src="https://github.com/hanHappy/MECHOORI/assets/118332255/e602e4fb-ae69-4219-96e4-792c704d85e1" width="50%" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>
