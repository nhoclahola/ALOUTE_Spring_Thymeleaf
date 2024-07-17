package com.nhoclahola.socialnetworkv1.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse
{
    private String jwtToken;
    private String message;
}
