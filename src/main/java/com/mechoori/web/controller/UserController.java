package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mechoori.web.entity.LikeList;
import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.Rate;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.MemberService;
import com.mechoori.web.service.RateService;
import com.mechoori.web.service.RestaurantService;
import com.mechoori.web.service.LikeListService;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MemberService service;
    @Autowired
    private RateService rateService;
    @Autowired
    private LikeListService likeService;


    @GetMapping("login")
    public String login() {
        return "user/login";
    }
    @PostMapping("login")
    public String afterLogin(@RequestParam("returnUrl")String returnUrl) {
        return "redirect:"+returnUrl;
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
    public String rateList(Model model,
               @AuthenticationPrincipal MechooriUserDetails user) {

      List<Rate> list  = rateService.getList(user.getId());
        model.addAttribute("list",list);

        System.out.println(list);

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
    @GetMapping("/my-page/like-list")
    public String likeList(
        Model model,
        @AuthenticationPrincipal MechooriUserDetails member){
        
        List<LikeList> list = likeService.getList(member.getId());
        // System.out.println(list);

        // for(LikeList likeList: list){
        //     likeList.add(LikeList.get)
        // }

        // (memberId=1, 
        // restaurantId=191, 
        // restaurantName=옥정, 
        // img=옥정.jpg, 
        // avgRatedPrice=11200, 
        // avgPrice=10500, 
        // valuePercentage=106)
        model.addAttribute("list", list);


        return "user/my-page/like-list";
    }




    // @GetMapping("my-page/like-list")
    // public String likeList(){
    //     return "user/my-page/like-list";
    // }

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

    //가성비 성과 페이지
    @GetMapping("my-page/statistics2")
    public String rateStatistics2(){

        return "user/my-page/statistics2";
    }
  

    //가성비 성과 페이지
    @GetMapping("my-page/statistics3")
    public String rateStatistics3(){

        return "user/my-page/statistics3";
    }

}
