package com.nhoclahola.socialnetworkv1.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public enum ErrorCode
{
    // Auth


    // User
    USER_NOT_EXIST(1200, "User does not exist", HttpStatus.BAD_REQUEST),

    // Post
    POST_NOT_EXIST(1300, "Post does not exist", HttpStatus.BAD_REQUEST),

    // Comment

    // Reels
    
    ;
    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
