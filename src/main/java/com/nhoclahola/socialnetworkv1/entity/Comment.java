package com.nhoclahola.socialnetworkv1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment
{
    @Id
    private String commentId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToMany(mappedBy = "likedComments", fetch = FetchType.LAZY)
    private Set<User> liked;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist()
    {
        this.commentId = "comment-" + UUID.randomUUID().toString();
    }
}
