package com.IntranetManagement.IntranetManagement.repositories;

import com.IntranetManagement.IntranetManagement.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
