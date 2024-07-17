package com.nhoclahola.socialnetworkv1.repository;

import com.nhoclahola.socialnetworkv1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String>
{
    @Query("SELECT p FROM Post p WHERE p.user.userId = :userId")
    public abstract List<Post> findPostByUserId(String userId);
}
