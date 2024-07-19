package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.reels.request.ReelsCreateRequest;
import com.nhoclahola.socialnetworkv1.dto.reels.response.ReelsResponse;
import com.nhoclahola.socialnetworkv1.entity.Reels;
import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.exception.AppException;
import com.nhoclahola.socialnetworkv1.exception.ErrorCode;
import com.nhoclahola.socialnetworkv1.mapper.ReelsMapper;
import com.nhoclahola.socialnetworkv1.repository.ReelsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReelsServiceImplementation implements ReelsService
{
    private final ReelsRepository reelsRepository;
    private final UserService userService;
    private final ReelsMapper reelsMapper;

    @Override
    @Transactional
    public ReelsResponse createReels(ReelsCreateRequest request)
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(userEmail);
        Reels reels = reelsMapper.reelCreateRequestToReels(request);
        reels.setUser(user);
        reelsRepository.save(reels);
        return reelsMapper.toReelsResponse(reels);
    }

    @Override
    public List<ReelsResponse> findAllReels()
    {
        List<Reels> reels = reelsRepository.findAll();
        return reelsMapper.toReelsResponse(reels);
    }

    @Override
    public List<ReelsResponse> findUsersReels(String userId)
    {
        List<Reels> reels = reelsRepository.findByUserId(userId);
        return reelsMapper.toReelsResponse(reels);
    }

    @Override
    public Reels findReelsById(String reelsId)
    {
        return reelsRepository.findById(reelsId)
                .orElseThrow(() -> new AppException(ErrorCode.REELS_NOT_EXIST));
    }

    @Override
    public ReelsResponse findReelsByIdResponse(String reelsId)
    {
        Reels reels = reelsRepository.findById(reelsId)
                .orElseThrow(() -> new AppException(ErrorCode.REELS_NOT_EXIST));
        return reelsMapper.toReelsResponse(reels);
    }
}
