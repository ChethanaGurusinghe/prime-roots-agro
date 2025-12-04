package edu.icet.model.dto;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class NotificationDTO {
    private Long id;
    private String title;
    private String message;
    private String type;
    private Boolean readFlag;
    private Instant createdAt;

    private Long userId;
}
