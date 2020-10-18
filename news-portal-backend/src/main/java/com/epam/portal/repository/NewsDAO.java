package com.epam.portal.repository;

import com.epam.portal.entity.News;

import java.util.List;

public interface NewsDAO {

    long saveOrUpdateNews(News news);

    News getNewsById(long id);

    List<News> getAllNews();

    void deleteNews(long id);
}
