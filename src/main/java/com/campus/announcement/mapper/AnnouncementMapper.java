package com.campus.announcement.mapper;

import com.campus.announcement.model.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AnnouncementMapper {
    Announcement findById(@Param("id") Long id);
    List<Announcement> findAll();
    int insertAnnouncement(Announcement announcement);
    int updateAnnouncement(Announcement announcement);
    int deleteAnnouncement(@Param("id") Long id);
    List<Announcement> findTopAnnouncements();
} 