//package com.epam.portal.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Entity
//public class News {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_generator")
//    @SequenceGenerator(name = "news_id_generator", sequenceName = "NEWS_ID_SEQ", allocationSize = 1)
//    private long id;
//
//    private String title;
//
//    private String brief;
//
//    private String content;
//
//    @Column(name = "NEWS_DATE")
//    private LocalDateTime newsDate;
//
//    public News() {
//    }
//
//    public News(String title, String brief, String content, LocalDateTime newsDate) {
//        this.title = title;
//        this.brief = brief;
//        this.content = content;
//        this.newsDate = newsDate;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getBrief() {
//        return brief;
//    }
//
//    public void setBrief(String brief) {
//        this.brief = brief;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public LocalDateTime getNewsDate() {
//        return newsDate;
//    }
//
//    public void setNewsDate(LocalDateTime newsDate) {
//        this.newsDate = newsDate;
//    }
//
//    @Override
//    public String toString() {
//        return "News{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", brief='" + brief + '\'' +
//                ", content='" + content + '\'' +
//                ", newsDate='" + newsDate + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        News news = (News) o;
//        return id == news.id &&
//                Objects.equals(title, news.title) &&
//                Objects.equals(brief, news.brief) &&
//                Objects.equals(content, news.content) &&
//                Objects.equals(newsDate, news.newsDate);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, title, brief, content, newsDate);
//    }
//}
