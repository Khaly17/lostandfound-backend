package sn.work.lostandfound.notification;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import sn.work.lostandfound.myException.NotFoundException;
import sn.work.lostandfound.myException.ObjectAlreadyExistsException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class NotificationServiceImpl implements NotificationService{
    private final NotificationConverter notificationConverter;
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(
            NotificationConverter notificationConverter,
            NotificationRepository notificationRepository
            ){
        this.notificationConverter = notificationConverter;
        this.notificationRepository = notificationRepository;
    }
    @Override
    public NotificationDto addNotification(NotificationDto notificationDto) {
        Notification notification = notificationConverter.convertToEntity(notificationDto);
        try {
            Notification notificationSaved = notificationRepository.save(notification);
            return notificationConverter.convertToDto(notificationSaved);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectAlreadyExistsException("notification already exists.");
        }
    }

    @Override
    public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {
        Notification notificationToUpdate = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("notification not found with id " + id));
        notificationToUpdate.setMotif(notificationDto.getMotif());
        notificationToUpdate.setDescription(notificationDto.getDescription());

        Notification notificationSaved = notificationRepository.save(notificationToUpdate);
        return notificationConverter.convertToDto(notificationSaved);
    }

    @Override
    public List<NotificationDto> findAllNotification() {
        List<Notification> notificationList = notificationRepository.findAll();
        return notificationList.stream().map(notificationConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<NotificationDto> findAllNotificationByUserId(Long userId) {
        return notificationRepository.findNotificationsByUserId(userId);
    }

    public List<NotificationDto> getAllNotificationByUserId(Long userId){
        List<NotificationDto> notificationDtoList = notificationRepository.findAll().stream().map(notificationConverter::convertToDto).collect(Collectors.toList());
        return  notificationDtoList.stream()
                .filter(notif -> notif.getUserId() == userId)
                .collect(Collectors.toList());
    }
}
