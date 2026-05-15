package com.darkSProject.ticketBooking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String name;
    private String hashPassword;
    @OneToMany(mappedBy = "user")
    private List<Ticket> ticketBooked;
    @Column(unique = true)
    private String email;
}
