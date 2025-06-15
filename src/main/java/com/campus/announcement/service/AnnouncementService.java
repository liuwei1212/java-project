package com.campus.announcement.service;

import com.campus.announcement.model.Announcement;
import java.util.List;

public interface AnnouncementService {
    Announcement findById(Long id);
    List<Announcement> findAll();
    boolean addAnnouncement(Announcement announcement);
    boolean updateAnnouncement(Announcement announcement);
    boolean deleteAnnouncement(Long id);
    List<Announcement> findTopAnnouncements();
} 