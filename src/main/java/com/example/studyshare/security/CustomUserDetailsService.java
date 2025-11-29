package com.example.studyshare.security;

import com.example.studyshare.entities.Admin;
import com.example.studyshare.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = repo.findByUsername(username);
        return new CustomUserDetails(admin);
    }
}

