package com.epam.portal.service;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.exception.BusinessException;

import java.util.List;

public interface NewsService {

    NewsDTO saveOrUpdateNews(NewsDTO news) throws BusinessException;

    NewsDTO getNewsById(long id);

    List<NewsDTO> getAllNews();

    List<NewsDTO> getAllNewsByLanguage(String language);

    void deleteNews(long id);

    void deleteNews(List<Long> id);

}
