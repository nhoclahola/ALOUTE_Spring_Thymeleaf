package com.nhoclahola.socialnetworkv1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post
{
    @Id
    private String postId;
    private String caption;
    private String imageUrl;
    private String videoUrl;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // EAGER to respond quickly in PostResponse
    @ManyToMany(mappedBy = "likedPost", fetch = FetchType.LAZY)
    private List<User> liked;

    @ManyToMany(mappedBy = "savedPost", fetch = FetchType.LAZY)
    private List<User> saved;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @PrePersist
    public void prePersist()
    {
        this.postId = "post-" + UUID.randomUUID().toString();
    }
}
