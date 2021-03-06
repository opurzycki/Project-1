package com.example.taskapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;
    @NotNull
    private String surname;

    @Email
    private String email;

    @Size(min = 5)
    private String password;
    private int active;
    private String department;
    private Date dateAdded;
    private String role;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "user")
    List<Task> tasks;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user_message")
    List<Message> messages;



}
