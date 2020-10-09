package com.epam.portal.service;

import com.epam.portal.entity.News;

import java.util.List;

public interface NewsService {

    long saveOrUpdateNews(News news);

    News getNewsById(long id);

    List<News> getAllNews();

    void deleteNews(News news);
}
