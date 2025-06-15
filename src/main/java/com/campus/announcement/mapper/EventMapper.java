package com.campus.announcement.mapper;

import com.campus.announcement.model.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface EventMapper {
    Event findById(@Param("id") Long id);
    List<Event> findAll();
    int insertEvent(Event event);
    int updateEvent(Event event);
    int deleteEvent(@Param("id") Long id);
} 