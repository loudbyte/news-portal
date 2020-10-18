package com.epam.portal.service;

import com.epam.portal.dto.NewsDTO;

import java.util.List;

public interface NewsService {

    long saveOrUpdateNews(NewsDTO news);

    NewsDTO getNewsById(long id);

    List<NewsDTO> getAllNews();

    void deleteNews(long id);
}
