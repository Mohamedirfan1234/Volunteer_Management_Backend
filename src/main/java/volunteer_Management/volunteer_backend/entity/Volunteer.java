package volunteer_Management.volunteer_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phone;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("volunteers") // Prevent infinite loop
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties("volunteers") // Prevent infinite loop
    private Event event;
}
