package com.epam.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class News {

    @Id
    private long id;

    private String title;

    private String brief;

    private String content;

    @Column(name = "NEWS_DATE")
    private LocalDateTime newsDate;

    public News() {
    }

    public News(long id, String title, String brief, String content, LocalDateTime newsDate) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.newsDate = newsDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(LocalDateTime newsDate) {
        this.newsDate = newsDate;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", newsDate='" + newsDate + '\'' +
                '}';
    }
}
