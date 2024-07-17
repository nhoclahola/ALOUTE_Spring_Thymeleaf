package com.nhoclahola.socialnetworkv1.repository;

import com.nhoclahola.socialnetworkv1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String>
{

}
