package com.nhoclahola.socialnetworkv1.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest
{
    @NotBlank(message = "You must enter the first name")
    private String firstName;
    @NotBlank(message = "You must enter the last name")
    private String lastName;
    @NotBlank(message = "You must enter the email")
    private String email;
    @NotBlank(message = "You must enter the username")
    private String username;
    @NotBlank(message = "You must enter the password")
    private String password;
    private String gender;
}
