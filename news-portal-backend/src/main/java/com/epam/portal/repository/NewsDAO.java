package com.epam.portal.repository;

import com.epam.portal.entity.News;

import java.util.List;

public interface NewsDAO {

    News saveOrUpdateNews(News news);

    News getNewsById(long id);

    List<News> getAllNews();

    List<News> getAllNewsByLanguage(String language);

    void deleteNews(long id);
}
