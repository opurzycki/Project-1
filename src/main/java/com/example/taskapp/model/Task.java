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
public class Task {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String title;
    @NotNull
    private String content;
    private String tag;
    private int status;
}
