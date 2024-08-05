package com.nhoclahola.socialnetworkv1.repository;

import com.nhoclahola.socialnetworkv1.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String>
{
    // JOIN FETCH to make it fetch eagerly, even I set lazy fetch in entity
    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.post.postId = :postId")
    public abstract List<Comment> findCommentsByPostId(String postId, Pageable pageable);
}
