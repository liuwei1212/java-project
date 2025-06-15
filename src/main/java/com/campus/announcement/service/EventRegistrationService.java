package com.campus.announcement.service;

import com.campus.announcement.model.EventRegistration;
import java.util.List;

public interface EventRegistrationService {
    EventRegistration findById(Long id);
    List<EventRegistration> findByEventId(Long eventId);
    boolean addRegistration(EventRegistration registration);
    boolean deleteRegistration(Long id);
    List<EventRegistration> findAll();
    EventRegistration findByEventIdAndUserId(Long eventId, Long userId);
} 