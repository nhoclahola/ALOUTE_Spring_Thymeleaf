package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.reels.request.ReelsCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.reels.response.ReelsResponse;
import com.nhoclahola.socialnetworkv1.entity.Reels;

import java.util.List;

public interface ReelsService
{
    public abstract ReelsResponse createReels(ReelsCreateRequest request);

    public abstract List<ReelsResponse> findAllReels();

    public abstract List<ReelsResponse> findUsersReels(String userId);

    public abstract Reels findReelsById(String reelsId);

    public abstract ReelsResponse findReelsByIdResponse(String reelsId);
}
