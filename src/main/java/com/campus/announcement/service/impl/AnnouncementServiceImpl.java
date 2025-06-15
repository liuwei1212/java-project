package com.campus.announcement.service.impl;

import com.campus.announcement.mapper.AnnouncementMapper;
import com.campus.announcement.model.Announcement;
import com.campus.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public Announcement findById(Long id) {
        return announcementMapper.findById(id);
    }

    @Override
    public List<Announcement> findAll() {
        return announcementMapper.findAll();
    }

    @Override
    public boolean addAnnouncement(Announcement announcement) {
        return announcementMapper.insertAnnouncement(announcement) > 0;
    }

    @Override
    public boolean updateAnnouncement(Announcement announcement) {
        return announcementMapper.updateAnnouncement(announcement) > 0;
    }

    @Override
    public boolean deleteAnnouncement(Long id) {
        return announcementMapper.deleteAnnouncement(id) > 0;
    }

    @Override
    public List<Announcement> findTopAnnouncements() {
        return announcementMapper.findTopAnnouncements();
    }
} 