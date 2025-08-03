package volunteer_Management.volunteer_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volunteer_Management.volunteer_backend.entity.Event;
import volunteer_Management.volunteer_backend.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        try {
            Event savedEvent = eventService.saveEvent(event);
            return ResponseEntity.ok(savedEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to create event: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to fetch events: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        try {
            Event updated = eventService.updateEvent(id, updatedEvent);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Update failed: " + e.getMessage());
        }
    }
    @PostMapping("/send/{id}")

    public ResponseEntity<?> sendEventToOrg(@PathVariable Long id) {
        // TODO: Add logic to send email / data to organization
        return ResponseEntity.ok("Event ID " + id + " sent to organization!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok("Event deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Delete failed: " + e.getMessage());
        }
    }




    
}
