package com.darkSProject.ticketBooking.train.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "coach")
@Entity
public class Coach {

    @Id
    private String coachId;

    private String coachName;

    @ManyToOne
    private Train train;

    @OneToMany(mappedBy = "coach")
    private List<Seat> seats;
}