package volunteer_Management.volunteer_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import volunteer_Management.volunteer_backend.entity.Volunteer;

import java.util.List;


public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    List<Volunteer> findByEventId(Long eventId);

   void deleteByEventId(Long eventId);


}
