package com.mechoori.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

@PostMapping("category")
    public String category(
                            @RequestParam("name") String[] name, 
                            @RequestParam("image") String[] image, 
                            int[] id) {
        
        for(int i = 0; i < id.length; i++)
            System.out.printf("name: %s / image: %s / id: %d\n", name[i], image[i], id[i]);

        // if(id!=null&&(name!=null||image!=null))
        //     service.update(name, image, id);
        // else if(name!=null&&image!=null)
        //     service.save(name, image);
        // else if(id!=null)
        //     service.delete(id);

        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/edit")
    public String editCategory(int id, Model model){
        Category category = service.getDetail(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("/admin/category/edit")
    public String category(Category category){
        service.update(category);
        return "redirect:/admin/category";
    }
}