package com.mechoori.web.controller;

import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.Statistics;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.MemberService;
import com.mechoori.web.service.RateService;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private MemberService service;

    @Autowired
    private RateService rateService;

    @Autowired
	private ResourceLoader resourceLoader;
	
	@Value("${upload.path}")
	private String uploadPath;

    @GetMapping("login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/login/find-id")
    public String findId() {
        return "user/login/find-id";
    }

    @GetMapping("/login/find-id-result")
    public String findIdResult() {
        return "user/login/find-id-result";
    }

    @GetMapping("/login/find-pwd")
    public String findPwd() {
        return "user/login/find-pwd";
    }

    @GetMapping("/login/find-pwd-result")
    public String findPwdResult() {
        return "user/login/find-pwd-result";
    }


    @GetMapping("sign-up/policy")
    public String policy() {
        return "user/sign-up/policy";
    }

    @GetMapping("sign-up/form")
    public String form() {
        return "user/sign-up/form";
    }

    @PostMapping("sign-up/form")
    public String form(Member member) {
        service.add(member);
        return "redirect:/";
    }
    @GetMapping("sign-up/complete")
    public String complete(){
        return "user/sign-up/complete";
    }

    //내 정보
    @GetMapping("my-page")
    public String myPage(){
        return "user/my-page";
    }

    //내 정보 변경
    @GetMapping("my-page/edit-info")
    public String editInfo(){
        return "user/my-page/edit-info";
    }

    //내 닉네임 변경
    @GetMapping("my-page/edit-info/nickname")
    public String editNickname(){
        return "user/my-page/edit-info/nickname";
    }

    //내 휴대폰 번호 변경
    @GetMapping("my-page/edit-info/phone")
    public String editPhone() {
        return "user/my-page/edit-info/phone";
    }

    @GetMapping("my-page/edit-info/email")
    public String editEmail() {
        return "user/my-page/edit-info/email";
    }

    //비밀번호 변경
    @GetMapping("my-page/edit-info/pwd")
    public String changePwd(){
        return "user/my-page/edit-info/pwd";
    }

    //회원탈퇴
    @GetMapping("my-page/edit-info/withdraw")
    public String withdraw() {
        return "user/my-page/edit-info/withdraw";
    }  
    // 회원탈퇴 완료 페이지
    @GetMapping("my-page/edit-info/withdraw-complete")
    public String withdrawComplete() {
        return "user/my-page/edit-info/withdraw-complete";
    }

    //찜한목록
    @GetMapping("my-page/like-list")
    public String likeList(){
        return "user/my-page/like-list";
    }

    @GetMapping("my-page/rate-list")
    public String rateList(){
        return "user/my-page/rate-list";
    }

    //가성비 평가목록 비교
    @GetMapping("my-page/statistics")
    public String rateStatistics(Model model, @AuthenticationPrincipal MechooriUserDetails member){
        

        
        Map<String, Statistics> data = rateService.getData(member.getId());
        
        model.addAttribute("data", data);
        return "user/my-page/statistics";
    }

    // 사진 등록 
    @GetMapping("reg")
	public String reg() {
		return "user/my-page/reg";
	}

    @PostMapping("{id}/image")
	public List<String> add(@RequestParam("file") MultipartFile[] files, @PathVariable int id) throws IOException {
		List<String> returnFiles = new ArrayList<>();
		
		for(MultipartFile file : files)
		{
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			
	//		String uploadPath="/upload/menu/c/1/2023/";
			Resource resource = resourceLoader.getResource(uploadPath);
			
			//Unix에서는 파일과 폴더가 구분되어 있지 않습니다.
			File pathFile = resource.getFile();
	//		pathFile.mkdir();
			if(pathFile.exists())
				pathFile.mkdirs();
			
			
	//		안좋은 방법 : String realPath = pathFile.getAbsolutePath()+"\\"+fileName;
	//		조금 나은 방법 : String realPath = pathFile.getAbsolutePath()+File.separator+fileName;
			String realPath = Paths.get(pathFile.getAbsolutePath(),fileName).toString();
			
			System.out.println(realPath);
			
			file.transferTo(new File(realPath));
			
			returnFiles.add(Paths.get(uploadPath, fileName).toString());
		}
		
		return returnFiles;
	}
}
