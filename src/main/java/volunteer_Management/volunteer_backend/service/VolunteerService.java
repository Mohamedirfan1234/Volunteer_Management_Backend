package volunteer_Management.volunteer_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volunteer_Management.volunteer_backend.entity.Event;
import volunteer_Management.volunteer_backend.entity.User;
import volunteer_Management.volunteer_backend.entity.Volunteer;
import volunteer_Management.volunteer_backend.repository.EventRepository;
import volunteer_Management.volunteer_backend.repository.UserRepository;
import volunteer_Management.volunteer_backend.repository.VolunteerRepository;

import java.util.List;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public Volunteer registerVolunteer(Long userId, Long eventId, String fullName, String phone) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Volunteer volunteer = new Volunteer();
        volunteer.setUser(user);
        volunteer.setEvent(event);
        volunteer.setStatus("REGISTERED");
        volunteer.setFullName(fullName);
        volunteer.setPhone(phone);

        return volunteerRepository.save(volunteer);
    }
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }
    public List<Volunteer> getVolunteersByEvent(Long eventId) {
        return volunteerRepository.findByEventId(eventId);
    }
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
    public Volunteer updateVolunteer(Long id, Volunteer updated) {
        Volunteer existing = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));

        existing.setFullName(updated.getFullName());
        existing.setPhone(updated.getPhone());
        return volunteerRepository.save(existing);
    }





}
