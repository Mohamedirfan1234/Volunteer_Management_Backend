package volunteer_Management.volunteer_backend.repository;
import volunteer_Management.volunteer_backend.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}

