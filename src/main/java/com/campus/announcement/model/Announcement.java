package com.campus.announcement.model;

import java.util.Date;

public class Announcement {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Boolean isTop;
    private Date createTime;

    // getter 和 setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public Boolean getIsTop() { return isTop; }
    public void setIsTop(Boolean isTop) { this.isTop = isTop; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
} 