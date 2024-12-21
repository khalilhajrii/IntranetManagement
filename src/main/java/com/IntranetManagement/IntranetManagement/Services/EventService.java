package com.IntranetManagement.IntranetManagement.Services;

import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Entities.Event;
import com.IntranetManagement.IntranetManagement.Entities.User;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import com.IntranetManagement.IntranetManagement.repositories.EventRepository;
import com.IntranetManagement.IntranetManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    public Event createEvent(Event event, Integer departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        event.setDepartment(department);
        Event savedEvent = eventRepository.save(event);
        notifyUsers(department, savedEvent);
        return savedEvent;
    }

    private void notifyUsers(Department department, Event event) {
        List<User> users = department.getUsers();
        for (User user : users) {
            sendEmailNotification(user.getEmail(), event);
        }
    }

    private void sendEmailNotification(String to, Event event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("New Event in Your Department: " + event.getTitle());
        message.setText("Dear User,\n\nA new event has been added to your department:\n\n" +
                "Title: " + event.getTitle() + "\n" +
                "Description: " + event.getDescription() + "\n" +
                "Start Time: " + event.getStartTime() + "\n" +
                "End Time: " + event.getEndTime() + "\n\n" +
                "Please check your department events for more details.\n\n" +
                "Best Regards,\nIntranet Management Team [Fatma, Khouloud, Khalil, Saif]");
        mailSender.send(message);

    }

    public Event updateEvent(Long eventId, Event updatedEvent, Integer departmentId) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());
        existingEvent.setDepartment(department);
        return eventRepository.save(existingEvent);
    }


    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public List<Event> getEventsByDepartment(Long departmentId) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getDepartment().getId().equals(departmentId))
                .toList();
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
    }

}
