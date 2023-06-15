package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mechoori.web.entity.TopCategory;
import com.mechoori.web.service.CategoryService;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CategoryService ctgService;

    @GetMapping("")
    public String admin() {
        return "/admin";
    }

    @GetMapping("category")
    public List<TopCategory> category(Model model) {
        List<TopCategory> list = ctgService.getTopCategoryList();
        model.addAttribute("list", list);
        return list;
    }

    @GetMapping("category/save")
    public String saveTopCategory() {
        return "admin/category/save";
    }

    @PostMapping("category/save")
    public String saveTopCategory(TopCategory category) {
        ctgService.saveTopCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("category/edit")
    public String editTopCategory(int id, Model model) {
        TopCategory category = ctgService.getTopCategoryDetail(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("category/edit")
    public String editTopCategory(TopCategory category) {
        ctgService.updateTopCategory(category);
        return "redirect:category";
    }

    @PostMapping("category")
    public String deleteTopCategory(int id){
        return "redirect:";
    }
}
