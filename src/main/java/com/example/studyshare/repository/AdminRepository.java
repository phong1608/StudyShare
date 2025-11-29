package com.example.studyshare.repository;

import com.example.studyshare.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    boolean existsAdminByUsername(String username) ;
    Admin findByUsername(String username);

}
