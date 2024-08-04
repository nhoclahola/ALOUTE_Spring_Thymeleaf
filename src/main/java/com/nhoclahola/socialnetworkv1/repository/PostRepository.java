package com.nhoclahola.socialnetworkv1.repository;

import com.nhoclahola.socialnetworkv1.dto.post.PostWithLikes;
import com.nhoclahola.socialnetworkv1.entity.Post;
import com.nhoclahola.socialnetworkv1.entity.User;
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
    @Query("SELECT new com.nhoclahola.socialnetworkv1.dto.post.PostWithLikes(p.postId, p.caption, p.imageUrl, p.videoUrl, p.createdAt, p.user, (SELECT COUNT(l) FROM p.liked l), (SELECT COUNT(c) FROM p.comments c)) " +
            "FROM Post p WHERE p.user IN (SELECT f FROM User u JOIN u.followings f WHERE u.id = :currentUserId)")
    public abstract List<PostWithLikes> findPostsFromFollowedUsers(@Param("currentUserId") String currentUserId, Pageable pageable);

    // Get a list of random posts from the other users whom the currentUser is not following
    @Query("SELECT new com.nhoclahola.socialnetworkv1.dto.post.PostWithLikes(p.postId, p.caption, p.imageUrl, p.videoUrl, p.createdAt, p.user, (SELECT COUNT(l) FROM p.liked l), (SELECT COUNT(c) FROM p.comments c)) " +
            "FROM Post p WHERE p.user NOT IN (SELECT f FROM User u JOIN u.followings f WHERE u.id = :currentUserId) ORDER BY function('RAND')")
    public abstract List<PostWithLikes> findRandomPostsFromOtherUsers(@Param("currentUserId") String currentUserId, Pageable pageable);

    // Check if a user liked a post or not
    @Query("SELECT COUNT(p) > 0 FROM Post p JOIN p.liked u WHERE p.postId = :postId AND u = :user")
    public abstract boolean isLikedByUser(@Param("postId") String postId, @Param("user") User user);
}
