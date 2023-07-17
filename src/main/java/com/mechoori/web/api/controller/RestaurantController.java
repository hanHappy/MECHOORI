package com.mechoori.web.api.controller;

import com.mechoori.web.entity.RestaurantRankView;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiRestaurantController")
@RequestMapping("api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService rstrService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public List<RestaurantView> list(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "tc", required = false) Integer topCtgId,
            @RequestParam(name = "c", required = false) Integer ctgId,
            @RequestParam(name = "f", required = false) Integer filterId,
            @RequestParam("o") int offset,
            @AuthenticationPrincipal MechooriUserDetails member) {

        List<RestaurantView> list = null;

        Integer memberId = null;

        if (member != null)
            memberId = member.getId();

        // 식당 리스트 출력
        if (query == null && ctgId == null && topCtgId == null && filterId == null)
            list = rstrService.getRestaurantViewList(memberId, offset);
        else if (topCtgId != null && filterId != null)
            list = rstrService.getRestaurantViewListByTopCtgIdAndFilter(memberId, topCtgId, filterId, offset);
        else if (filterId != null)
            list = rstrService.getRestaurantViewListByFilter(memberId, ctgId, filterId, offset);
        else if (query != null)
            list = rstrService.getRestaurantViewListByQuery(memberId, query, offset);
        else if (topCtgId != null)
            list = rstrService.getRestaurantViewListByTopCtgId(memberId, topCtgId, offset);
        else if (ctgId != null)
            list = rstrService.getRestaurantViewListByCtgId(memberId, ctgId, offset);

        return list;
    }


    @GetMapping("/ranking")
    public List<RestaurantRankView> list(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "ctgId", required = false) Integer categoryId) {

        List<RestaurantRankView> list = null;

        if (categoryId != null)
            list = rstrService.getRanking(categoryId, offset);
        else
            list = rstrService.getRanking(null, offset);

        return list;
    }



}