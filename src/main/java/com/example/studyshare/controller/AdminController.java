package com.example.studyshare.controller;


import com.example.studyshare.entities.Admin;
import com.example.studyshare.services.AdminService;
import com.example.studyshare.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AdminController(AdminService adminService,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {
        adminService.CreateAdmin(admin);
        return ResponseEntity.ok("Registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin loginRequest) {

        Admin admin = adminService.getAdminByUserName(loginRequest.getUsername());

        if (admin == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtService.generateToken(admin);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "username", admin.getUsername(),
                "id", admin.getAdminId()
        ));
    }
}

