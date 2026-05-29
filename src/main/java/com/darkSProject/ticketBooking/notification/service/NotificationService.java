package com.darkSProject.ticketBooking.notification.service;

import com.darkSProject.ticketBooking.notification.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SimpMessagingTemplate
            messagingTemplate;

    public void sendNotification(
            NotificationDTO notification
    ) {

        messagingTemplate.convertAndSend(

                "/topic/notifications/" +
                        notification.getUserId(),

                notification
        );
    }
}
