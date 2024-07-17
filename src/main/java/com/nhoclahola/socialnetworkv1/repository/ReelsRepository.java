package com.nhoclahola.socialnetworkv1.repository;

import com.nhoclahola.socialnetworkv1.entity.Reels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelsRepository extends JpaRepository<Reels, String>
{
    @Query("SELECT p FROM Reels p WHERE p.user.userId = :userId")
    public abstract List<Reels> findByUserId(@Param("userId") String userId);
}
