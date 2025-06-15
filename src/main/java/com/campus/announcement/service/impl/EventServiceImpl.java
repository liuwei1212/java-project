package com.campus.announcement.service.impl;

import com.campus.announcement.mapper.EventMapper;
import com.campus.announcement.model.Event;
import com.campus.announcement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventMapper eventMapper;

    @Override
    public Event findById(Long id) {
        return eventMapper.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return eventMapper.findAll();
    }

    @Override
    public boolean addEvent(Event event) {
        return eventMapper.insertEvent(event) > 0;
    }

    @Override
    public boolean updateEvent(Event event) {
        return eventMapper.updateEvent(event) > 0;
    }

    @Override
    public boolean deleteEvent(Long id) {
        return eventMapper.deleteEvent(id) > 0;
    }
} 