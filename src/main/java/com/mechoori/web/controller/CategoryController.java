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
    public String category(Model model){
    
        //조회
        List<Category> list = service.getList();
        model.addAttribute("list", list);

        //삽입
        // Category category = 

        return "admin/category";
    }
    
    @PostMapping("/admin/category")
    public String category(String name, String image, int[] id){

        if(name != null && image != null)
            service.save(name,image);
        else if(id != null)
            service.delete(id);
        return "redirect:/admin/category";
    }
    
    

}
