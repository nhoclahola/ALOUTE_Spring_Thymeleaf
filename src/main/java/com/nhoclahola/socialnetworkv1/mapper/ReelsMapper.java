package com.nhoclahola.socialnetworkv1.mapper;

import com.nhoclahola.socialnetworkv1.dto.reels.request.ReelsCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.reels.response.ReelsResponse;
import com.nhoclahola.socialnetworkv1.entity.Reels;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReelsMapper
{
    public abstract Reels reelCreateRequestToReels(ReelsCreateRequest request);

    public abstract ReelsResponse toReelsResponse(Reels reels);

    public abstract List<ReelsResponse> toReelsResponse(List<Reels> reels);
}
