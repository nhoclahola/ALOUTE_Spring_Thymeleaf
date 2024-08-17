package com.nhoclahola.socialnetworkv1.repository;

import com.nhoclahola.socialnetworkv1.entity.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String>
{
    @Query("SELECT n " +
            "FROM Notification n " +
            "JOIN FETCH n.user " +
            "JOIN FETCH n.triggerUser " +
            "JOIN FETCH n.post " +
            "WHERE n.post.user.userId = :userId")
    public abstract List<Notification> findNotificationByUserId(@Param("userId") String userId,
                                                                Pageable pageable);
}
