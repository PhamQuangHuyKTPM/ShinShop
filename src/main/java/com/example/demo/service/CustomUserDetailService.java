package com.example.demo.service;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.UserEntity;
import com.example.demo.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userService.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("Sai");
        }

        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        Set<UserRole> roles = user.getUserRoles();

        for(UserRole userRole : roles){
            grantedAuthoritySet.add(new SimpleGrantedAuthority(userRole.getRole().getRoleName()));
        }

        return new CustomUserDetails(user, grantedAuthoritySet);
    }


}
