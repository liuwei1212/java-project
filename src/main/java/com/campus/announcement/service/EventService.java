package com.campus.announcement.service;

import com.campus.announcement.model.Event;
import java.util.List;

public interface EventService {
    Event findById(Long id);
    List<Event> findAll();
    boolean addEvent(Event event);
    boolean updateEvent(Event event);
    boolean deleteEvent(Long id);
} 