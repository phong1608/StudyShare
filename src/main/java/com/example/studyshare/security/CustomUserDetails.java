package com.example.studyshare.security;

import com.example.studyshare.entities.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

    }
    public CustomUserDetails(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }
    public Long getAdminId(){
        return admin.getAdminId();
    }
    @Override
    public String getUsername() {
        return admin.getUsername();
    }
}
