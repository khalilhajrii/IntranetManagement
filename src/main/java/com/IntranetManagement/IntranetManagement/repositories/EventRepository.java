package com.IntranetManagement.IntranetManagement.repositories;

import com.IntranetManagement.IntranetManagement.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

}
