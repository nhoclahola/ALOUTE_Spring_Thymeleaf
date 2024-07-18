package com.nhoclahola.socialnetworkv1.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginRequest
{
    @NotBlank(message = "You must enter the email")
    private String email;
    @NotBlank(message = "You must enter the password")
    private String password;
}
