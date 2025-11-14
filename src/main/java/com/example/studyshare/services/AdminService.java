package com.example.studyshare.services;

import com.example.studyshare.entities.Admin;
import com.example.studyshare.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public Admin CreateAdmin(Admin admin) {

        return adminRepository.save(admin);
    }

}
