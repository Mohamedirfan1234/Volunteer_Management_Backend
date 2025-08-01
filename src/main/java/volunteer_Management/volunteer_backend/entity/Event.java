package volunteer_Management.volunteer_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;         // Changed from eventName to match frontend
    private String location;
    private String date;
    private String time;          // ✅ New
    private String capacity;      // ✅ New (or use int if preferred)
    private String description;
}
