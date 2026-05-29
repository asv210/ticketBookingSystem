package com.darkSProject.ticketBooking.notification.dto;

import lombok.*;

@Builder

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class NotificationDTO {

    private String userId;

    private String message;

    private String type;
}
