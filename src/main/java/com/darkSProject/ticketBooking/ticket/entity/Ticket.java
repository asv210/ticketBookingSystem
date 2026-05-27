package com.darkSProject.ticketBooking.ticket.entity;

import com.darkSProject.ticketBooking.train.entity.SeatBooking;
import com.darkSProject.ticketBooking.train.entity.Train;
import com.darkSProject.ticketBooking.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    private Date dateOfTravel;
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
    @CreatedDate
    private Date createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
