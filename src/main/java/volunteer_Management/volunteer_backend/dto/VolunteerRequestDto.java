package volunteer_Management.volunteer_backend.dto;

import lombok.Data;

@Data
public class VolunteerRequestDto {
    private Long userId;
    private Long eventId;
    private String fullName;
    private String phone;
}