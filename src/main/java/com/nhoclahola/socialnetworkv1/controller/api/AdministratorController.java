package com.nhoclahola.socialnetworkv1.controller.api;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.admin.response.DashBoardInfo;
import com.nhoclahola.socialnetworkv1.dto.comment.response.CommentWithDataResponse;
import com.nhoclahola.socialnetworkv1.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdministratorController
{
    private final AdminService adminService;

    @GetMapping("/dashboard")
    public ApiResponse<DashBoardInfo> getDashBoardInfo()
    {
        DashBoardInfo dashBoardInfo = adminService.getDashBoardInfo();
        ApiResponse<DashBoardInfo> response = new ApiResponse<>();
        response.setResult(dashBoardInfo);
        return response;
    }
}
