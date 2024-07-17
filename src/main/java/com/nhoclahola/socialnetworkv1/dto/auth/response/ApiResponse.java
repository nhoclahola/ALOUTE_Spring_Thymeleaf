package com.nhoclahola.socialnetworkv1.dto.auth.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse
{
    private String message;
    private boolean status;
}
