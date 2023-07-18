package com.mechoori.web.api.controller;


import com.mechoori.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/admin")
@RestController("apiAdminController")
public class AdminController {

    @Autowired
    private MenuService menuService;

    @DeleteMapping("/menu/{id}")
    public int deleteMenu(@PathVariable("id") int id) {

        System.out.println(id);

        return menuService.delete(id);
    }


}
