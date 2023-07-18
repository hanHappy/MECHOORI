package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mechoori.web.entity.LikeList;
import com.mechoori.web.entity.Member;
import com.mechoori.web.entity.RateListView;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.MemberService;
import com.mechoori.web.service.RateService;
import com.mechoori.web.service.RestaurantLikeService;
import com.mechoori.web.service.RestaurantService;

import jakarta.servlet.http.HttpServletRequest;

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
    private RestaurantLikeService restaurantLikeService;

    @GetMapping("/social-login")
    public String socialLogin(){
        return "user/login/social-login";
    }

    @GetMapping("login")
    public String login(HttpServletRequest request){

        // Referer -> 이전 페이지 URI 가져오기
        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }
        
        return "user/login";
    }

    @PostMapping("login")
    public String afterLogin(@RequestParam("returnUrl") String returnUrl) {
        return "redirect:" + returnUrl;
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
    public String resetPwd(Member member) {
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
    public String complete() {
        return "user/sign-up/complete";
    }


    @GetMapping("my-page/rate-list")
    public String rateList(Model model,
                           @RequestParam(value = "offset", defaultValue = "0") int offset,
                           @AuthenticationPrincipal MechooriUserDetails user) {

//        List<RateList> list = rateService.getList(user.getId());
        List<RateListView> list = rateService.getMyList(user.getId(),offset);
        model.addAttribute("list", list);

        return "user/my-page/rate-list";
    }


    //내 정보
    @GetMapping("my-page")
    public String myPage(@AuthenticationPrincipal MechooriUserDetails member, Model model){
        model.addAttribute("m", member);
        return "user/my-page";
    }

    //내 정보 변경
    @GetMapping("my-page/edit-info")
    public String editInfo(@AuthenticationPrincipal MechooriUserDetails member, Model model){
        model.addAttribute("m", member);
        return "user/my-page/edit-info";
    }

    //로그인되 있는 내정보를 꺼내기위함
    @PostMapping("my-page/edit-info")
    public String editInfo(Member member){
        service.add(member);
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
    public String changePwd() {
        return "user/my-page/edit-info/pwd";
    }


    //비밀번호 변경 POST
    @PostMapping("my-page/edit-info/pwd")
    public String changePwd(
        @RequestParam("newPwd") String newPwd,
        @AuthenticationPrincipal MechooriUserDetails user
    ){
        Member member = new Member();
        member.setId(user.getId());

        service.updatePassword(member, newPwd);
        return "redirect:my-page/edit-info";
    }
    // /user/my-page/edit-info/pwd




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

    @GetMapping("my-page/like-list")
    public String likeList(Model model, @AuthenticationPrincipal MechooriUserDetails member){

        List<LikeList> list = restaurantLikeService.getList(member.getId());

        model.addAttribute("list", list);

        return "user/my-page/like-list";
    }

    //가성비 성과 페이지
    @GetMapping("my-page/statistics")
    public String rateStatistics(){

        return "user/my-page/statistics";
    }
    
}
