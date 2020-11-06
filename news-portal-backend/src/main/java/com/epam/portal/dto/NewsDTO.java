package com.epam.portal.dto;

import java.util.Objects;

public class NewsDTO {

    private long id;

    private String title;

    private String brief;

    private String content;

    private String newsDate;

    private String language;

    public NewsDTO(long id, String title, String brief, String content, String newsDate, String language) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.newsDate = newsDate;
        this.language = language;
    }

    public NewsDTO() {
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

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO newsDTO = (NewsDTO) o;
        return id == newsDTO.id &&
                Objects.equals(title, newsDTO.title) &&
                Objects.equals(brief, newsDTO.brief) &&
                Objects.equals(content, newsDTO.content) &&
                Objects.equals(newsDate, newsDTO.newsDate) &&
                Objects.equals(language, newsDTO.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, brief, content, newsDate, language);
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", newsDate='" + newsDate + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}