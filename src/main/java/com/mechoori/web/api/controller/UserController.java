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

    @GetMapping("/statistics")
    public Map<String, Integer> statistics(
            @AuthenticationPrincipal MechooriUserDetails member) {

        Map<String, Integer> data = rateService.getDate(member.getId());
        System.out.println("data : " + data);

        return data;
    }

    // 이미지 추가
    @PostMapping("{id}/image")
    public String add(@RequestParam("file") MultipartFile file, @PathVariable int id) throws IOException {
        String returnFile = null;

        MultipartFile file1 = null;

        String fileName = file1.getOriginalFilename();
        System.out.println(fileName);

        Resource resource = resourceLoader.getResource(uploadPath);

        File pathFile = resource.getFile();

        if (pathFile.exists())
            pathFile.mkdirs();

        String realPath = Paths.get(pathFile.getAbsolutePath(), fileName).toString();

        System.out.println(realPath);

        file.transferTo(new File(realPath));

        String fullPath = uploadPath + fileName;

        Member member = service.getById(id);
        member.setImg(fullPath);
        // http://localhost:8080/user/my-page/statistics
        service.update(member);
        return returnFile;
    }
}