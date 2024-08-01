package com.nhoclahola.socialnetworkv1.repository;

import com.nhoclahola.socialnetworkv1.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String>
{
    @Query("SELECT p FROM Post p WHERE p.user.userId = :userId")
    public abstract List<Post> findPostByUserId(String userId);

    // JOIN FETCH to make it fetch eagerly, even I set lazy fetch in entity
    // Get a list of posts from the users who the current user is following
    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.user IN (SELECT f FROM User u JOIN u.followings f WHERE u.id = :currentUserId) ORDER BY p.createdAt DESC")
    List<Post> findPostsFromFollowedUsers(@Param("currentUserId") String currentUserId, Pageable pageable);

    // Get a list of random posts from the other users whom the currentUser is not following
    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.user NOT IN (SELECT f FROM User u JOIN u.followings f WHERE u.id = :currentUserId) ORDER BY function('RAND')")
    List<Post> findRandomPostsFromOtherUsers(@Param("currentUserId") String currentUserId, Pageable pageable);
}
