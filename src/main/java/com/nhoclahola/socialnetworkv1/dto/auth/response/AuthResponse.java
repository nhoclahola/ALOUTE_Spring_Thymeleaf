package com.nhoclahola.socialnetworkv1.dto.auth.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse
{
    private String jwtToken;
    private String message;
}
