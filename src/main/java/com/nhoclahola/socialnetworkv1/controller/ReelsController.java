package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import com.nhoclahola.socialnetworkv1.dto.reels.request.ReelsCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.reels.response.ReelsResponse;
import com.nhoclahola.socialnetworkv1.service.ReelsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReelsController
{
    private final ReelsService reelsService;
    @PostMapping("/reels")
    public ApiResponse<ReelsResponse> createReels(@RequestBody @Valid ReelsCreateRequest reelsCreateRequest)
    {
        ReelsResponse reelsResponse = reelsService.createReels(reelsCreateRequest);
        ApiResponse<ReelsResponse> response = new ApiResponse<>();
        response.setResult(reelsResponse);
        return response;
    }

    @GetMapping("/reels")
    public ApiResponse<List<ReelsResponse>> getAllReels()
    {
        List<ReelsResponse> reelsResponseList = reelsService.findAllReels();
        ApiResponse<List<ReelsResponse>> response = new ApiResponse<>();
        response.setResult(reelsResponseList);
        return response;
    }

    @GetMapping("/reels/{reelsId}")
    public ApiResponse<ReelsResponse> getReelsById(@PathVariable String reelsId)
    {
        ReelsResponse reelsResponse = reelsService.findReelsByIdResponse(reelsId);
        ApiResponse<ReelsResponse> response = new ApiResponse<>();
        response.setResult(reelsResponse);
        return response;
    }

    @GetMapping("/reels/users/{userId}")
    public ApiResponse<List<ReelsResponse>> getReelsByUserId(@PathVariable String userId)
    {
        List<ReelsResponse> reelsResponseList = reelsService.findUsersReels(userId);
        ApiResponse<List<ReelsResponse>> response = new ApiResponse<>();
        response.setResult(reelsResponseList);
        return response;
    }
}
