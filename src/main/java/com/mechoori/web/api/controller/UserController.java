package com.mechoori.web.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.RateListView;
import com.mechoori.web.security.MechooriUserDetailService;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.MemberService;
import com.mechoori.web.service.RateService;

@RestController("apiUserController")
@RequestMapping("api/user")
public class UserController {
    
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${upload.profile}")
    private String uploadPath;
    @Autowired
    private MemberService service;
    @Autowired
    private RateService rateService;
    @Autowired
    private MechooriUserDetailService mechooriUserDetailService;

    @GetMapping("/statistics")
    public Map<String, Integer> statistics(
            @AuthenticationPrincipal MechooriUserDetails member) {

        Map<String, Integer> data = rateService.getDate(member.getId());

        return data;
    }

    // TODO 이미지 파일명 + id로 저장
    // 이미지 추가
    @PutMapping("{id}")
    @Transactional
    public Member updateInfo(
        @PathVariable("id") int memberId,
        @RequestParam(name = "nickname", required = false) String nickname,
        @RequestParam(name =  "file", required = false) MultipartFile file) throws IOException {

        Member member = service.getById(memberId);
        
        // 프로필 사진 변경했다면~
        if(file != null){
            // 이미지 파일명
            String fileName = file.getOriginalFilename();
            
            // 1. 경로 설정 및 디렉토리 생성
            Resource resource = resourceLoader.getResource(uploadPath);
            File pathFile = resource.getFile();
    
            if(!pathFile.exists())
                pathFile.mkdirs();
    
            // 2. 파일 저장
            // 절대 경로
            // C:/~/롯데리아.jpg
            String realPath = Paths.get(pathFile.getAbsolutePath(), fileName).toString();
            // 파일.변신(새로운 파일(절대경로에))
            file.transferTo(new File(realPath));
    
            // 3. db 저장
            // /images/member/profile/롯데리아.jpg
            String dbPath = uploadPath + fileName;

            member.setImg(dbPath);
        }

        // 닉네임 변경했다면 ~
        if(nickname != null){
            member.setNickname(nickname);
        }
        
        service.update(member);
        
        // 세션 갱신 ==============================================
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MechooriUserDetails sessionMember = (MechooriUserDetails) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication, sessionMember.getEmail()));
        // -------------------------------------------------------
        
        return member;
    }

    // 세션 갱신 method
    public Authentication createNewAuthentication(Authentication currentAuth, String email) {
        UserDetails newPrincipal = mechooriUserDetailService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal,
                currentAuth.getCredentials(), newPrincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;
    }

    @GetMapping("/my-page/rate-list")
    public List <RateListView> rateList(
                           @AuthenticationPrincipal MechooriUserDetails user,
                           @RequestParam(value = "offset") int offset) {

        List<RateListView> list = rateService.getList(user.getId(),offset);

        return list;
    }
}