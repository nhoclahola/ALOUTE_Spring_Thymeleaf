package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.dto.admin.response.DashBoardInfo;
import com.nhoclahola.socialnetworkv1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService
{
    private final UserRepository userRepository;

    @Override
    public DashBoardInfo getDashBoardInfo()
    {
        return userRepository.adminDashBoardInfo();
    }
}
