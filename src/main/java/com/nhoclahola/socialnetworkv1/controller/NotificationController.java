package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.notification.response.NotificationResponse;
import com.nhoclahola.socialnetworkv1.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NotificationController
{
    private final NotificationService notificationService;

    @PutMapping("/notifications/{notificationId}/read")
    public ApiResponse<String> markNotificationAsRead(@PathVariable String notificationId)
    {
        String result = notificationService.markAsRead(notificationId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    @GetMapping("/notifications/users/{userId}")
    public ApiResponse<List<NotificationResponse>> getUsersNotifications(@PathVariable String userId, @RequestParam int index)
    {
        List<NotificationResponse> notificationResponseList = notificationService.findNotificationByUserId(userId, index);
        ApiResponse<List<NotificationResponse>> response = new ApiResponse<>();
        response.setResult(notificationResponseList);
        return response;
    }
}
