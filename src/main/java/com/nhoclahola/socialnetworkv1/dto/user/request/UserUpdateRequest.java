package com.nhoclahola.socialnetworkv1.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest
{
    @NotBlank(message = "You must enter the first name")
    private String firstName;
    @NotBlank(message = "You must enter the last name")
    private String lastName;
    private String password;
    private Boolean gender;
}
