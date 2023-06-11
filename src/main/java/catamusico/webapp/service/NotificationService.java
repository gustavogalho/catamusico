package catamusico.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catamusico.webapp.domain.Band;
import catamusico.webapp.domain.Musician;
import catamusico.webapp.domain.Notification;
import catamusico.webapp.repository.NotificationRepository;

@Service
public class NotificationService {
    @Autowired
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> findByMusicianId(Long musicianId) {
        return notificationRepository.findByMusicianId(musicianId);
    }

    public Notification save(Band band, Musician musician) {
        Notification notificationToSave = new Notification();
        notificationToSave.setBand(band);
        notificationToSave.setMusician(musician);
        notificationToSave.setMessage("Uma banda favoritou seu perfil!!");
        notificationToSave.setRead(false);
        return notificationRepository.save(notificationToSave);
    }

    public boolean newNotification(Long musicianId) {
        List<Notification> notifications = notificationRepository.findByMusicianId(musicianId);

        for (Notification notification : notifications) {
            if (!notification.getRead()) {
                return true;
            }
        }
        return false;
    }

    public Notification findByMusicianIdAndBandId(Long musicianId, Long badnId)
    {
        return notificationRepository.findByMusicianIdAndBandId(musicianId, badnId);
    }

    public Notification setRead(boolean read, Long notificationId){
        Notification notificationToSave = notificationRepository.getOne(notificationId);
        notificationToSave.setRead(read);

        return notificationRepository.save(notificationToSave);
    }
}
