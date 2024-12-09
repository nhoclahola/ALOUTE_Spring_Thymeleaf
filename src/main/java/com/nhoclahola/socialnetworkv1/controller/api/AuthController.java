package com.nhoclahola.socialnetworkv1.controller.api;

import com.nhoclahola.socialnetworkv1.dto.auth.request.UserCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.auth.request.UserLoginRequest;
import com.nhoclahola.socialnetworkv1.dto.auth.response.AuthResponse;
import com.nhoclahola.socialnetworkv1.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController
{
    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse createUser(@RequestBody @Valid UserCreateRequest request)
    {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid UserLoginRequest request)
    {
        return authService.authenticate(request);
    }
}
