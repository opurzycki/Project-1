package com.example.taskapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "task")
    List<TaskMessage> taskMessages;
}
