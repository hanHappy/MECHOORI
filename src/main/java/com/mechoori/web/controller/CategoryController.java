package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.service.TopCategoryService;

@Controller
public class CategoryController {

    @Autowired
    private TopCategoryService topCtgservice;

    @GetMapping("/admin/category")
    public String category(Model model) {

        // 조회
        List<TopCategory> list = topCtgservice.getList();

        // POST 데이터 담을 빈 객체
        model.addAttribute("list", list)
            .addAttribute("category", new TopCategory());

        return "admin/category";
    }

    @PostMapping("/admin/category")
    public String saveCategory(TopCategory category) {
            topCtgservice.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/edit")
    public String editCategory(int id, Model model){
        TopCategory category = topCtgservice.getDetail(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("/admin/category/edit")
    public String editCategory(TopCategory category){
        topCtgservice.update(category);
        return "redirect:/admin/category";
    }
}