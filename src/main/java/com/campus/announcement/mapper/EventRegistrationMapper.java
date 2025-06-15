package com.campus.announcement.mapper;

import com.campus.announcement.model.EventRegistration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface EventRegistrationMapper {
    EventRegistration findById(@Param("id") Long id);
    List<EventRegistration> findByEventId(@Param("eventId") Long eventId);
    int insertRegistration(EventRegistration registration);
    int deleteRegistration(@Param("id") Long id);
    List<EventRegistration> findAll();
    EventRegistration findByEventIdAndUserId(@Param("eventId") Long eventId, @Param("userId") Long userId);
} 