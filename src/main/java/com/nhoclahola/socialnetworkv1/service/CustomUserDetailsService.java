package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security
                .core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
