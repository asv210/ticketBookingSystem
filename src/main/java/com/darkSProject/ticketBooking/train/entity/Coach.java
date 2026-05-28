package com.darkSProject.ticketBooking.train.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Table(name = "coach")
@Entity
@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String coachId;

    private String coachName;
    @Enumerated(EnumType.STRING)
    private CoachType coachType;

    private Double farePerStation;

    @ManyToOne
    private Train train;

    @OneToMany(mappedBy = "coach")
    private List<Seat> seats;
}