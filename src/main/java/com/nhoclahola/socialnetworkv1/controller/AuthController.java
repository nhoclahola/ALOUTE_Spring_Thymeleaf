package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.configuration.JwtProvider;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.user.request.UserLoginRequest;
import com.nhoclahola.socialnetworkv1.dto.auth.response.AuthResponse;
import com.nhoclahola.socialnetworkv1.service.CustomUserDetailsService;
import com.nhoclahola.socialnetworkv1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController
{
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public AuthResponse createUser(@RequestBody UserCreateRequest request)
    {
        return userService.createUser(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserLoginRequest userLoginRequest)
    {
        Authentication authentication = authenticate(userLoginRequest.getEmail(), userLoginRequest.getPassword());
        String jwtToken = JwtProvider.generateJwtToken(authentication);
        return new AuthResponse(jwtToken, "Login success");
    }

    private Authentication authenticate(String email, String password)
    {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (userDetails == null)
            throw new BadCredentialsException("Invalid email");
        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Invalid password");
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
