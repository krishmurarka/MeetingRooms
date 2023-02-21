package com.meetingroom.Meeting.Room.Entitty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_table")

public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private  int id;
    @Column(name = "username", nullable = false, unique = true)
    @Size(min= 5)
    private String username;
    private String password;
}
