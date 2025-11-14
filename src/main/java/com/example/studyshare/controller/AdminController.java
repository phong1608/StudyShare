package com.example.studyshare.controller;


import com.example.studyshare.entities.Admin;
import com.example.studyshare.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AdminController {
    @Autowired
    private AdminService userService;

    @GetMapping("/")
    public String index(){
        return "Hello World";
    }
    @PostMapping("/create")
    public Admin create(@RequestBody Admin admin){
        return userService.CreateAdmin(admin);
    }
}
