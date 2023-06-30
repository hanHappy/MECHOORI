package com.mechoori.web.controller;

import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.service.MemberService;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MemberService service;

    @Autowired
    private RateService ratedService;


    @GetMapping("login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/login/find-id")
    public String findId() {
        return "user/login/find-id";
    }

    @GetMapping("/login/find-pwd")
    public String findPwd() {
        return "user/login/find-pwd";
    }



//    @GetMapping("/login/find-pwd-result")
//    public String findPwdResult(String email) {
//        return "user/login/find-pwd-result";
//    }

    @PostMapping("login/find-pwd")
    public String resetPwd(Member member)    {
        System.out.println(member);
        System.out.println(member.getPassword());
        service.resetPwd(member);

        return "redirect:/";
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
        return "redirect:/user/sign-up/complete";
    }
    @GetMapping("sign-up/complete")
    public String complete(){
        return "user/sign-up/complete";
    }


    @GetMapping("my-page/rate-list")
    public String rateList(Model model) {

        return "user/my-page/rate-list";
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

    //가성비 성과 페이지
    @GetMapping("my-page/statistics")
    public String rateStatistics(){
        // Integer memberId = null;
        // if (member != null) {
        //     memberId = member.getId();
        // }    
        //List<Rate> list = rateService.getRatedPrice(rate, memberId);
       // Map<String, Statistics> data = ratedService.getDate(member.getId());
        //model.addAttribute("data", data);
        return "user/my-page/statistics";
    }

    // reg-date,  

    // @GetMapping("/statistics")
    // public String statistics(
    //      @AuthenticationPrincipal MechooriUserDetails member) {
   
    //      Integer memberId = member.getId();

    //     if (memberId == null) {
    //     return "user/login";
    // }
    //     return "restaurant/statistics";
         
    // }   



    //가성비 성과페이지
    // 맴버 평가 평균 데이터, 유저 평가 평균 데이터
    // rate table에서 user_id, menu_id, price
    
    // 맴버 평균 데이터 : rate > user_id(전체), price(전체)
    // 유저 평균 데이터 : rate > user_id(한명), price

}
