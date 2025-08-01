package volunteer_Management.volunteer_backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volunteer_Management.volunteer_backend.entity.Event;
import volunteer_Management.volunteer_backend.repository.EventRepository;
import volunteer_Management.volunteer_backend.repository.VolunteerRepository;

import java.util.List;



@Service
public class EventService {


    @Autowired
    private EventRepository eventRepository;
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        existing.setTitle(updatedEvent.getTitle());
        existing.setLocation(updatedEvent.getLocation());
        existing.setDate(updatedEvent.getDate());
        existing.setTime(updatedEvent.getTime());
        existing.setCapacity(updatedEvent.getCapacity());
        existing.setDescription(updatedEvent.getDescription());

        return eventRepository.save(existing);
    }
    @Autowired
    private VolunteerRepository volunteerRepository;

    @Transactional
    public void deleteEvent(Long eventId) {
        // Step 1: Delete all volunteers for this event
        volunteerRepository.deleteByEventId(eventId);

        // Step 2: Delete the event itself
        eventRepository.deleteById(eventId);
    }











}
