package com.nhoclahola.socialnetworkv1.controller;

import com.nhoclahola.socialnetworkv1.dto.reels.request.ReelsCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.reels.response.ReelsResponse;
import com.nhoclahola.socialnetworkv1.service.ReelsService;
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
    public ReelsResponse createReels(@RequestBody ReelsCreateRequest reelsCreateRequest)
    {
        return reelsService.createReels(reelsCreateRequest);
    }

    @GetMapping("/reels")
    public List<ReelsResponse> getAllReels()
    {
        return reelsService.findAllReels();
    }

    @GetMapping("/reels/{reelsId}")
    public ReelsResponse getReelsById(@PathVariable String reelsId)
    {
        return reelsService.findReelsByIdResponse(reelsId);
    }

    @GetMapping("/reels/users/{userId}")
    public List<ReelsResponse> getReelsByUserId(@PathVariable String userId)
    {
        return reelsService.findUsersReels(userId);
    }
}
