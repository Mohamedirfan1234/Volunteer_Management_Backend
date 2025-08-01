package volunteer_Management.volunteer_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volunteer_Management.volunteer_backend.dto.VolunteerRequestDto;
import volunteer_Management.volunteer_backend.entity.Volunteer;
import volunteer_Management.volunteer_backend.service.VolunteerService;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin(origins = "*")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;


    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }


    @PostMapping("/volunteer/register")
    public ResponseEntity<?> registerVolunteer(@RequestBody VolunteerRequestDto dto) {
        Volunteer volunteer = volunteerService.registerVolunteer(
                dto.getUserId(),
                dto.getEventId(),
                dto.getFullName(),
                dto.getPhone()
        );
        return ResponseEntity.ok(volunteer);
    }
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Volunteer>> getVolunteersByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(volunteerService.getVolunteersByEvent(eventId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok("Volunteer deleted successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable Long id, @RequestBody Volunteer updatedVolunteer) {
        Volunteer v = volunteerService.updateVolunteer(id, updatedVolunteer);
        return ResponseEntity.ok(v);
    }




}
