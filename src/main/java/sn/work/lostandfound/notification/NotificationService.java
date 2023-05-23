package sn.work.lostandfound.notification;

import java.util.List;

/**
 * @author DIENG Khaly (MS2E)
 */
public interface NotificationService {
    /**
     *
     * @param notificationDto
     * @return
     */
    NotificationDto addNotification(NotificationDto notificationDto);

    /**
     *
     * @param id
     * @param notificationDto
     * @return
     */
    NotificationDto updateNotification(Long id, NotificationDto notificationDto);

    /**
     *
     * @return
     */
    List<NotificationDto> findAllNotification();

    /**
     *
     * @param id
     */
    void deleteNotification(Long id);

    /**
     *
     * @return
     */
    List<NotificationDto> findAllNotificationByUserId(Long userId);
}
