package com.nhoclahola.socialnetworkv1.dto.user.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest
{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
}
