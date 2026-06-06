package com.darkSProject.ticketBooking.ticket.entity;

import com.darkSProject.ticketBooking.train.entity.Train;
import com.darkSProject.ticketBooking.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;
    private String source;
    private String destination;
    private LocalDateTime dateOfTravel;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
    @OneToMany(
            mappedBy = "ticket",
            cascade = CascadeType.ALL
    )
    private List<SeatBooking> seatBookings;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime expireAt;

    private Double totalFare;
}
