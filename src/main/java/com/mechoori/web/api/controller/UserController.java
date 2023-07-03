package com.mechoori.web.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechoori.web.entity.Statistics2;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.RateService;

@RestController("apiRateController")
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private RateService rateService;

    @GetMapping("/statistics")
    public Map<String, Integer> statistics(
            @AuthenticationPrincipal MechooriUserDetails member) {

        Map<String, Integer> data = rateService.getDate(member.getId());
        // System.out.println("data : " + data);

        return data;
    }

    @GetMapping("my-page/statistics2")
    public List<Statistics2> statistics2(
            @AuthenticationPrincipal MechooriUserDetails member) {

        List<Statistics2> data = rateService.getDate2(member.getId());
        System.out.println("data : " + data);

        return data;
    }
    // http://localhost:8080/user/my-page/statistics
}
