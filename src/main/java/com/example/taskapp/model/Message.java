package com.example.taskapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String content;
    private String tag;

    @ManyToOne
    @JoinColumn(name = "user_message_id")
    User user_message;


}
