package sn.work.lostandfound.notification;

import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {
    public NotificationDto convertToDto(Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setUserId(notification.getUserId());
        notificationDto.setObjId(notification.getObjId());
        notificationDto.setMotif(notification.getMotif());
        notificationDto.setDescription(notification.getDescription());
        notificationDto.setCreatedAt(notification.getCreatedAt());
        return notificationDto;
    }

    public Notification convertToEntity(NotificationDto notificationDto){
        Notification notification = new Notification();
        notification.setId(notificationDto.getId());
        notification.setUserId(notificationDto.getUserId());
        notification.setObjId(notificationDto.getObjId());
        notification.setMotif(notificationDto.getMotif());
        notification.setDescription(notificationDto.getDescription());
        notification.setCreatedAt(notificationDto.getCreatedAt());
        return notification;
    }
}
