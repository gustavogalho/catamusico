package catamusico.webapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import catamusico.webapp.domain.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List <Notification> findByMusicianId(Long musicianId);

    Notification findByMusicianIdAndBandId(Long musicianId, Long badnId);

}
