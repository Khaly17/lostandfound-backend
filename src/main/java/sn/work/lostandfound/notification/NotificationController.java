package sn.work.lostandfound.notification;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/find")
public class NotificationController {
    private final NotificationServiceImpl notificationService;
    public NotificationController(
            NotificationServiceImpl notificationService
    ){
        this.notificationService = notificationService;
    }

    @GetMapping("/notification/all")
    public List<NotificationDto> findNotificationAll(){
        return this.notificationService.findAllNotification();
    }

    @PostMapping("/notification/add")
    public NotificationDto addNotification(@RequestBody NotificationDto notificationDto){
        return  notificationService.addNotification(notificationDto);
    }

    @DeleteMapping("/notification/delete/{id}")
    public void deleteNotification(@PathVariable("id") Long id){
        notificationService.deleteNotification(id);
    }

    @PutMapping("/notification/update/{id}")
    public NotificationDto updateNotification(@PathVariable("id") Long id, @RequestBody NotificationDto notificationDto){
        return notificationService.updateNotification(id,notificationDto);
    }

    @GetMapping("/notification/user/{userId}")
    public List<NotificationDto> findNotificationsUserId(@PathVariable Long userId){
        return this.notificationService.getAllNotificationByUserId(userId);
    }

}
