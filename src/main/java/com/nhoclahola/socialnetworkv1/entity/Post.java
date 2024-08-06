package com.nhoclahola.socialnetworkv1.entity;

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
    @Column(nullable = false)
    private String caption;
    private String imageUrl;
    private LocalDateTime createdAt;

    // EAGER to avoid many querys in getting posts
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Use Set to force Hibernate create primary key for join table of @ManyToMany

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name ="post_liked",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> liked;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name ="post_saved",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> saved;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @PrePersist
    public void prePersist()
    {
        this.postId = "post-" + UUID.randomUUID().toString();
    }
}
