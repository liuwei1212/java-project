package com.campus.announcement.model;

import java.util.Date;

public class EventRegistration {
    private Long id;
    private Long eventId;
    private Long userId;
    private Date registerTime;

    // getter 和 setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Date getRegisterTime() { return registerTime; }
    public void setRegisterTime(Date registerTime) { this.registerTime = registerTime; }
} 