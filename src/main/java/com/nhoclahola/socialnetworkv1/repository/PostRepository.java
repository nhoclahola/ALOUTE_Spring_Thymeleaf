package com.nhoclahola.socialnetworkv1.repository;

import com.nhoclahola.socialnetworkv1.dto.post.PostWithData;
import com.nhoclahola.socialnetworkv1.entity.Post;
import com.nhoclahola.socialnetworkv1.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String>
{
    @Query("SELECT new com.nhoclahola.socialnetworkv1.dto.post.PostWithData(p.postId, p.caption, p.imageUrl, p.videoUrl, p.createdAt, p.user, " +
            "(SELECT COUNT(l) FROM p.liked l), " +
            "(SELECT COUNT(c) FROM p.comments c), " +
            "EXISTS (SELECT l FROM p.liked l WHERE l.userId = :requestUserId)) " +
            "FROM Post p WHERE p.user.id = :userId")
    public abstract List<PostWithData> findPostByUserId(@Param("requestUserId") String requestUserId,
                                                        @Param("userId") String userId, Pageable pageable);

    // Get a list of posts from the users who the current user is following
    @Query("SELECT new com.nhoclahola.socialnetworkv1.dto.post.PostWithData(p.postId, p.caption, p.imageUrl, p.videoUrl, p.createdAt, p.user, " +
            "(SELECT COUNT(l) FROM p.liked l), " +
            "(SELECT COUNT(c) FROM p.comments c), " +
            "EXISTS (SELECT l FROM p.liked l WHERE l.userId = :currentUserId)) " +
            "FROM Post p WHERE p.user IN (SELECT f FROM User u JOIN u.followings f WHERE u.id = :currentUserId)")
    public abstract List<PostWithData> findPostsFromFollowedUsers(@Param("currentUserId") String currentUserId, Pageable pageable);

    // Get a list of random posts from the other users whom the currentUser is not following
    @Query("SELECT new com.nhoclahola.socialnetworkv1.dto.post.PostWithData(p.postId, p.caption, p.imageUrl, p.videoUrl, p.createdAt, p.user, " +
            "(SELECT COUNT(l) FROM p.liked l), " +
            "(SELECT COUNT(c) FROM p.comments c), " +
            "EXISTS (SELECT l FROM p.liked l WHERE l.userId = :currentUserId)) " +
            "FROM Post p WHERE p.user NOT IN (SELECT f FROM User u JOIN u.followings f WHERE u.id = :currentUserId) ORDER BY function('RAND')")
    public abstract List<PostWithData> findRandomPostsFromOtherUsers(@Param("currentUserId") String currentUserId, Pageable pageable);

    // Check if a user liked a post or not
    // ***Note: Change email to username later
    @Query(value = "SELECT EXISTS (SELECT 1 " +
            "FROM post_liked p " +
            "WHERE p.post_id = :postId AND p.user_id = :userId)", nativeQuery = true)
    public abstract int isLikedByUserId(@Param("postId") String postId, @Param("userId") String userId);

    @Modifying
    @Query(value = "DELETE FROM post_liked " +
            "WHERE post_id = :postId " +
            "AND user_id = :userId", nativeQuery = true)
    void removeLikeFromPost(@Param("postId") String postId, @Param("userId") String userId);

    @Modifying
    @Query(value = "INSERT INTO post_liked (post_id, user_id) " +
            "VALUES (:postId, :userId)", nativeQuery = true)
    void addLikeToPost(@Param("postId") String postId, @Param("userId") String userId);
}
