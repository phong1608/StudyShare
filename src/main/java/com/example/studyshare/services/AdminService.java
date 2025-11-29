package com.example.studyshare.services;

import com.example.studyshare.entities.Admin;
import com.example.studyshare.repository.AdminRepository;
import com.example.studyshare.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {
    @Autowired
    private  AdminRepository adminRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;



    public Admin CreateAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public Admin getAdminByUserName(String userName) {
        return adminRepository.findByUsername(userName);
    }

    public boolean existsByUsername(String userName) {
        return adminRepository.existsAdminByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(admin);
    }
}

