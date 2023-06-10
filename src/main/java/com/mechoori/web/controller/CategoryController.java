package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mechoori.web.entity.Category;
import com.mechoori.web.service.CategoryService;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/admin/category")
    public String category(Model model) {

        // 조회
        List<Category> list = service.getList();

        // POST 데이터 담을 빈 객체
        model.addAttribute("list", list)
            .addAttribute("category", new Category());

        return "admin/category";
    }

    @PostMapping("/admin/category")
    public String category(Category category, int[] id) {
        if (id != null)
            service.delete(id);
        else
            service.save(category);
        return "redirect:/admin/category";
    }

}
