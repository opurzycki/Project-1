package com.example.taskapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    private int user_id;
    private String tag;



}
