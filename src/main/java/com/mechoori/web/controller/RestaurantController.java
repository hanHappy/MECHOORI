package com.mechoori.web.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map;

import org.bouncycastle.math.raw.Mod;
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

import com.mechoori.web.entity.Category;
import com.mechoori.web.entity.Menu;
import com.mechoori.web.entity.MenuView;
import com.mechoori.web.entity.Rate;
import com.mechoori.web.entity.Restaurant;
import com.mechoori.web.entity.RestaurantDetail;
import com.mechoori.web.entity.RestaurantRankView;
import com.mechoori.web.entity.RestaurantView;
import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.security.MechooriUserDetails;
import com.mechoori.web.service.CategoryService;
import com.mechoori.web.service.MenuService;
import com.mechoori.web.service.RateService;
import com.mechoori.web.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RateService rateService;

    @Value("${upload.review}")
    private String uploadPath;
    @Autowired
    private ResourceLoader resourceLoader;


    @GetMapping("/list")
    public String list(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "c", required = false) Integer ctgId,
            @AuthenticationPrincipal MechooriUserDetails member,
            Model model) {

        List<TopCategory> mainCtgList = categoryService.getTopCategoryList();
        List<Category> otherCtgList = categoryService.getOtherCategoryList();

        List<RestaurantView> list = null;

        Integer memberId = null;

        int offset = 0;

        if (member != null)
            memberId = member.getId();

        // 식당 리스트 출력
        if (query == null && ctgId == null)
            list = restaurantService.getRestaurantViewList(memberId, offset);
        else if (query != null)
            list = restaurantService.getRestaurantViewListByQuery(memberId, query, offset);
        else if (ctgId != null)
            list = restaurantService.getRestaurantViewListByCtgId(memberId, ctgId, offset);

        model.addAttribute("list", list)
                .addAttribute("mainCtgList", mainCtgList)
                .addAttribute("otherCtgList", otherCtgList);

        return "restaurant/list";
    }

    @GetMapping("{id}")
    public String detail(
            @PathVariable("id") int restaurantId,
            @AuthenticationPrincipal MechooriUserDetails member,
            Model model) {
        
        Integer memberId = null;
        if(member!=null)
            memberId = member.getId();

        // List<Menu> menuList = menuService.getList(restaurantId);
        RestaurantView restaurantView = restaurantService.getViewDetailById(memberId, restaurantId);
        List<MenuView> menuViewList = menuService.getViewListByRestaurantId(restaurantId);

        //아이디
        List<Integer> menuIds = new ArrayList<>();
        for (MenuView menuView : menuViewList) {
            menuIds.add(menuView.getId());
        }
        //리뷰
        List<Rate> rateList = rateService.getListByMenuIds(menuIds);

        //리뷰 최신순 4개
        List<Rate> top4Rates;
        if (rateList.size() < 4) {
            List<Rate> sortedList = new ArrayList<>(rateList);
            sortedList.sort(Comparator.comparing(Rate::getRegDate).reversed());
            top4Rates = sortedList.subList(0, rateList.size());
        } else {
            List<Rate> sortedList = new ArrayList<>(rateList);
            sortedList.sort(Comparator.comparing(Rate::getRegDate).reversed());
            top4Rates = sortedList.subList(0, 4);
        }

        List<String> menuNames = new ArrayList<>();
        for (Rate rate : top4Rates) {
            int menuId = rate.getMenuId();
            String menuName = menuService.getMenuName(menuId, menuViewList);
            menuNames.add(menuName);
        }

        model.addAttribute("menuViewList", menuViewList);
        model.addAttribute("r", restaurantView);
        model.addAttribute("rateList", rateList);
        model.addAttribute("menuNames", menuNames);
        model.addAttribute("top4Rates", top4Rates);

        return "restaurant/detail";
    }

    @GetMapping("{id}/rate")
    public String rate(@PathVariable("id") int restaurantId, Model model) {

        Restaurant restaurant = restaurantService.getDetailById(restaurantId);
        List<Menu> menuList = menuService.getList(restaurantId);

        model.addAttribute("menuList", menuList)
                .addAttribute("r", restaurant);

        return "restaurant/rate";
    }

    // TODO 리뷰 이미지 파일명 + id로 저장
    @PostMapping("{id}/rate")
    public String rate(
            Rate rate,
            @RequestParam(name = "image", required = false) MultipartFile image,
            @AuthenticationPrincipal MechooriUserDetails user) throws IOException {
                
        // 리뷰 이미지 ---------------------------------------
        if(image != null && !image.isEmpty()){
            // input으로 넘어온 이미지 파일명
            String fileName = image.getOriginalFilename();
            
            Resource resource = resourceLoader.getResource(uploadPath);
            
            File pathFile = resource.getFile();
    
            // 디렉토리 없을 시 생성
            if(!pathFile.exists())
                pathFile.mkdirs();
            
            // 디렉토리에 이미지 저장
            // C:\Workspace\MECHOORI\src\main\webapp\images\member\review\가츠벤또.jpg
            String realPath = Paths.get(pathFile.getAbsolutePath(), fileName).toString();
            image.transferTo(new File(realPath));
    
            // /images/member/review/가츠벤또.jpg
            String fullPath = uploadPath + fileName;
    
            // 평가 데이터에 이미지 경로 세팅
            rate.setImg(fullPath);
        }

        // 평가 데이터 추가
        rateService.add(rate, user.getId());

        return "redirect:rate-result";
    }

//get mapping
//model -> map<>  =
// data 


    @GetMapping("/ranking")
        public String ranking(Model model,
        @RequestParam(value = "offset", defaultValue = "0") int offset,
        Integer categoryId) {

            List<TopCategory> mainCtgList = categoryService.getTopCategoryList();

            List<RestaurantRankView> list = null;

            if (categoryId != null) {
                list = restaurantService.getRanking(categoryId, offset);
            }
            else
                list = restaurantService.getRanking(categoryId, offset);

            model.addAttribute("list", list);
            model.addAttribute("ctg", mainCtgList);

        return "/restaurant/ranking";
    }

    @GetMapping("/mapPage/{id}")
    public String map(
            @PathVariable("id") int restaurantId,
            Model model) {

        Restaurant restaurant = restaurantService.getDetailById(restaurantId);
        RestaurantDetail res = restaurantService.getRestaurantDetailById(restaurantId);

        model.addAttribute("list", restaurant);
        model.addAttribute("r", res);

        return "restaurant/mapPage";
    }

    @GetMapping("{id}/rate-result")
    public String rateResult(
        @AuthenticationPrincipal MechooriUserDetails member, 
        @PathVariable("id") int restaurantId, 
        Model model){
        
        Map<String, Object> result = rateService.getRateResult(restaurantId, member.getId());
        model.addAttribute("result", result);

        return "/restaurant/rate-result";
    }


}