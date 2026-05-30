package com.notification_service.notification_service.service;

import com.leave_service.leave_service.entity.Leave;
import com.notification_service.notification_service.dto.LeaveNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    @KafkaListener(topics = "Notification",groupId = "Notification-Group")
    public void  consumeLeaveNotification(Leave leaveNotification) {
        log.info("consumeLeaveNotification:{}",leaveNotification);

    }
}
