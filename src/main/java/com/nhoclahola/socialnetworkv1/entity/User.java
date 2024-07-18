package com.nhoclahola.socialnetworkv1.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    private String userId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String gender;
    @Column(nullable = false)
    private Role role;

    @Builder.Default
    private List<String> followers = new ArrayList<>();
    @Builder.Default
    private List<String> followings = new ArrayList<>();

    // Use Set to force Hibernate create primary key for join table of @ManyToMany

    // Post

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)  // Field user of Post class
    private List<Post> posts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name ="post_liked",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "post_id") }
    )
    private Set<Post> likedPost;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name ="post_saved",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "post_id") }
    )
    private Set<Post> savedPost;


    // Comment

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name ="comment_liked",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "comment_id") }
    )
    private Set<Comment> likedComments;

    // Reels

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reels> reels;

    // Chat

    @ManyToMany(mappedBy = "users")
    private List<Chat> chats;

    @PrePersist
    public void prePersist()
    {
        this.userId = "user-" + UUID.randomUUID().toString();
    }
}


