package com.mechoori.web.api.controller;

import java.io.File;
import java.io.IOException;
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

import com.mechoori.web.entity.Statistics;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.MemberService;
import com.mechoori.web.service.RateService;


@RestController("apiRateController")
@RequestMapping("api/user")
public class UserController {
    
    // @Autowired
	// private ResourceLoader resourceLoader;
	
	// @Value("${upload.path}")
	// private String uploadPath;
    
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

    // @PostMapping("{id}/image")
	// public List<String> add(@RequestParam("file") MultipartFile[] files, @PathVariable int id) throws IOException {
	// 	List<String> returnFiles = new ArrayList<>();
		
	// 	MultipartFile file = files[0];

    //     String fileName = file.getOriginalFilename();
	// 	System.out.println(fileName);

    //     Resource resource = resourceLoader.getResource(uploadPath);

    //     File pathFile = resource.getFile();

    //     if(pathFile.exists())
	// 			pathFile.mkdirs();

    //     String realPath = Paths.get(pathFile.getAbsolutePath(),fileName).toString();

    //     System.out.println(realPath);
			
    //     file.transferTo(new File(realPath));
        
    //     String fullPath = uploadPath + fileName;
        
    //     returnFiles.add(Paths.get(uploadPath, fileName).toString());

    // User user = service.get(id);
    // user.setImg(fullPath);
    // service.update(img);
    // return returnFiles;
    //http://localhost:8080/user/my-page/statistics
}
