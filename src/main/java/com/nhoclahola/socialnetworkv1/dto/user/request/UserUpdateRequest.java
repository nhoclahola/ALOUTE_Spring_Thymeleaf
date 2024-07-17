package com.nhoclahola.socialnetworkv1.dto.user.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest
{
    private String firstName;
    private String lastName;
    private String password;
    private String gender;
}
