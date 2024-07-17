package com.nhoclahola.socialnetworkv1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reels
{
    @Id
    private String reelsId;
    private String title;
    private String videoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist()
    {
        this.reelsId = "reels-" + UUID.randomUUID().toString();
    }
}
