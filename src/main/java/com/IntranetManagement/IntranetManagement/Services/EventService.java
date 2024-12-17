package com.IntranetManagement.IntranetManagement.Services;

import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Entities.Event;
import com.IntranetManagement.IntranetManagement.Entities.User;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import com.IntranetManagement.IntranetManagement.repositories.EventRepository;
import com.IntranetManagement.IntranetManagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }


    public Event createEvent(Event event, Long departmentId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Vérifier si l'utilisateur est admin
        if (user.getIsAdmin() == null || user.getIsAdmin() != 1) {
            throw new RuntimeException("Only admins can create events.");
        }

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        event.setDepartment(department); // Associer l'événement au département.
        return eventRepository.save(event);
    }

    // Ajouter un événement au calendrier personnel d'un utilisateur
    public Event addEventToUser(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getEvents().add(event);
        userRepository.save(user);

        return event;
    }

    public Event updateEvent(Long eventId, Event updatedEvent, Long departmentId, Long userId) {
        // Vérifier si l'utilisateur existe et s'il est admin
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getIsAdmin() == null || user.getIsAdmin() != 1) {
            throw new RuntimeException("Only admins can update events.");
        }

        // Récupérer l'événement existant
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Mettre à jour les champs de l'événement
        event.setTitle(updatedEvent.getTitle());
        event.setDescription(updatedEvent.getDescription());
        event.setStartTime(updatedEvent.getStartTime());
        event.setEndTime(updatedEvent.getEndTime());

        // Mettre à jour le département associé
        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new RuntimeException("Department not found."));
            event.setDepartment(department);
        }

        // Sauvegarder l'événement mis à jour
        return eventRepository.save(event);
    }



    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }







}
