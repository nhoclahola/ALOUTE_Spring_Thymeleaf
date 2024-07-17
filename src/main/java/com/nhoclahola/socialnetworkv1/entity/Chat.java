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
public class Chat
{
    @Id
    private String chatId;
    private String chatName;
    private String chatImageUrl;
    private LocalDateTime timeStamp;

    // Use Set to force Hibernate create primary key for join table
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private List<Message> messages;

    @PrePersist
    public void prePersist()
    {
        this.chatId = "chat-" + UUID.randomUUID().toString();
    }
}
