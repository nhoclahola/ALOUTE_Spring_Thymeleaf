package com.nhoclahola.socialnetworkv1.dto.user.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginRequest
{
    private String email;
    private String password;
}
