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
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Créer un événement pour des départements
    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event,
                                             @RequestParam Long departmentId,
                                             @RequestParam Long userId) {

        return ResponseEntity.ok(eventService.createEvent(event, departmentId, userId));
    }

    @PutMapping("/{eventId}/update")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId,
                                             @RequestBody Event updatedEvent,
                                             @RequestParam Long departmentId,
                                             @RequestParam Long userId) {
        Event event = eventService.updateEvent(eventId, updatedEvent, departmentId, userId);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    // Ajouter un événement au calendrier personnel d’un utilisateur
    @PostMapping("/{eventId}/add-to-user/{userId}")
    public ResponseEntity<Event> addEventToUser(@PathVariable Long eventId, @PathVariable Long userId) {
        return ResponseEntity.ok(eventService.addEventToUser(eventId, userId));
    }


}
