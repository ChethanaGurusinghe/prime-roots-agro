package edu.icet.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateNotificationRequest {
    private Long id;
    private Boolean readFlag;
}
