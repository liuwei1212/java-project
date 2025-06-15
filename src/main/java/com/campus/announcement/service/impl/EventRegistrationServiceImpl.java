package com.campus.announcement.service.impl;

import com.campus.announcement.mapper.EventRegistrationMapper;
import com.campus.announcement.model.EventRegistration;
import com.campus.announcement.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventRegistrationServiceImpl implements EventRegistrationService {
    @Autowired
    private EventRegistrationMapper eventRegistrationMapper;

    @Override
    public EventRegistration findById(Long id) {
        return eventRegistrationMapper.findById(id);
    }

    @Override
    public List<EventRegistration> findByEventId(Long eventId) {
        return eventRegistrationMapper.findByEventId(eventId);
    }

    @Override
    public boolean addRegistration(EventRegistration registration) {
        return eventRegistrationMapper.insertRegistration(registration) > 0;
    }

    @Override
    public boolean deleteRegistration(Long id) {
        return eventRegistrationMapper.deleteRegistration(id) > 0;
    }

    @Override
    public java.util.List<com.campus.announcement.model.EventRegistration> findAll() {
        return eventRegistrationMapper.findAll();
    }

    @Override
    public EventRegistration findByEventIdAndUserId(Long eventId, Long userId) {
        return eventRegistrationMapper.findByEventIdAndUserId(eventId, userId);
    }
} 