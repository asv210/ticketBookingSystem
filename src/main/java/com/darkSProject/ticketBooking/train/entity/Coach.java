package com.darkSProject.ticketBooking.train.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "coach")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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