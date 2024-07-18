package com.nhoclahola.socialnetworkv1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message
{
    @Id
    private String messageId;
    @Column(nullable = false)
    private String content;
    private String imageUrl;
    private LocalDateTime timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @PrePersist
    public void prePersist()
    {
        this.messageId = "message-" + UUID.randomUUID().toString();
    }
}
