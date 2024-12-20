package com.IntranetManagement.IntranetManagement.Controller;


import com.IntranetManagement.IntranetManagement.Entities.Event;
import com.IntranetManagement.IntranetManagement.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")

public class EventController {
    @Autowired
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event, @RequestParam Integer departmentId) {
        return ResponseEntity.ok(eventService.createEvent(event, departmentId));
    }
    @PutMapping("/{eventId}/update")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody Event event, @RequestParam Integer departmentId) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, event, departmentId));
    }
    @DeleteMapping("/{eventId}/delete")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Event>> getEventsByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(eventService.getEventsByDepartment(departmentId));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

}
