package edu.icet.service;

import edu.icet.model.dto.NotificationDTO;
import edu.icet.model.entity.NotificationEntity;
import edu.icet.model.request.UpdateNotificationRequest;
import edu.icet.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationDTO create(String msg) {
        NotificationEntity n = NotificationEntity.builder()
                .message(msg)
                .read(false)
                .build();

        return toDTO(repo.save(n));
    }

    public List<NotificationDTO> getUnread() {
        return repo.findByReadFalse().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private NotificationDTO toDTO(NotificationEntity n) {
        return NotificationDTO.builder()
                .notificationId(n.getNotificationId())
                .message(n.getMessage())
                .read(n.isRead())
                .createdAt(n.getCreatedAt())
                .build();
    }

}
