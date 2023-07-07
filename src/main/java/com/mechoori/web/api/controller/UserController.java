package com.mechoori.web.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mechoori.web.entity.Member;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.MemberService;
import com.mechoori.web.service.RateService;


@RestController("apiRateController")
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

    @GetMapping("/statistics")
    public Map<String, Integer> statistics(
        @AuthenticationPrincipal MechooriUserDetails member){

        Map<String, Integer> data = rateService.getDate(member.getId());
        System.out.println("data : "+  data);

        return data;
    }


    // 이미지 추가
    @PutMapping("{id}/image")
	public String updateProfileImage(@PathVariable("id") int memberId, @RequestParam("file") MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    // yml 파일에 설정 -> upload : profile : "/image"

    // 1. 경로 설정 및 디렉토리 생성
    Resource resource = resourceLoader.getResource(uploadPath);
    File pathFile = resource.getFile();
    
    if(!pathFile.exists())
        pathFile.mkdirs();

    // 2. 파일 저장
    // 절대 경로 + 이미지 파일명 
        String realPath = Paths.get(pathFile.getAbsolutePath(), fileName).toString();
    // 파일.변신(새로운 파일(절대경로에))
        file.transferTo(new File(realPath));
        

    // 3. db에 ㅈㅓ장
    String dbPath = uploadPath + fileName;
    
    Member member = service.getById(memberId);
    
    member.setImg(dbPath);
    
    // 4. (마지막) 파일 경로 return -> edit-info
    service.update(member);

    return "";
}
}