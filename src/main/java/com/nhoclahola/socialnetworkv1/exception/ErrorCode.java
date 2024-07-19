package com.nhoclahola.socialnetworkv1.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public enum ErrorCode
{
    // Auth and validation
    UNAUTHENTICATED(1100, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1101, "You do not have permission to do this", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(1102, "Invalid Token", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(1103, "Invalid request", HttpStatus.BAD_REQUEST),
    USER_EXIST_REGISTER(1104, "This email has been used", HttpStatus.CONFLICT),

    // User
    USER_NOT_EXIST(1200, "The user does not exist", HttpStatus.NOT_FOUND),

    // Post
    POST_NOT_EXIST(1300, "The post does not exist", HttpStatus.NOT_FOUND),

    // Comment
    COMMENT_NOT_EXIST(1400, "The comment does not exist", HttpStatus.BAD_REQUEST),

    // Reels
    REELS_NOT_EXIST(1500, "The reels does not exist", HttpStatus.BAD_REQUEST),

    // Chat
    CHAT_NOT_EXIST(1600, "The chat does not exist", HttpStatus.BAD_REQUEST),

    // MESSAGE
    MESSAGE_NOT_EXIST(1700, "The message does not exist", HttpStatus.BAD_REQUEST),
    ;
    private final int responseCode;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
