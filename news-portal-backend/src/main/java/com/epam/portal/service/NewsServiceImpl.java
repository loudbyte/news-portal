package com.epam.portal.service;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.entity.News;
import com.epam.portal.exception.BusinessException;
import com.epam.portal.repository.NewsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsDAO newsDAO;

    @Override
    public long saveOrUpdateNews(NewsDTO news) throws BusinessException {
        return newsDAO.saveOrUpdateNews(convertNewsDTOToEntity(news));
    }

    @Override
    public NewsDTO getNewsById(long id) {
        return convertNewsEntityToDTO(newsDAO.getNewsById(id));
    }

    @Override
    public List<NewsDTO> getAllNews() {
        List<NewsDTO> newsDTOList = new ArrayList<>();
        List<News> newsList = newsDAO.getAllNews();
        for (News news : newsList) {
            newsDTOList.add(convertNewsEntityToDTO(news));
        }
        return newsDTOList;
    }

    @Override
    public void deleteNews(long id) {
        newsDAO.deleteNews(id);
    }

    @Override
    public void deleteNews(List<Long> listId) {
        for (Long id : listId) {
            newsDAO.deleteNews(id);
        }
    }

    @Autowired
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    private NewsDTO convertNewsEntityToDTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setBrief(news.getBrief());
        newsDTO.setContent(news.getContent());
        newsDTO.setNewsDate(news.getNewsDate().toString());
        return newsDTO;
    }

    private News convertNewsDTOToEntity(NewsDTO newsDTO) throws BusinessException {
        News news = new News();
        news.setId(newsDTO.getId());
        news.setTitle(newsDTO.getTitle());
        news.setBrief(newsDTO.getBrief());
        news.setContent(newsDTO.getContent());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(newsDTO.getNewsDate(), formatter);
        } catch (Exception exception) {
            throw new BusinessException("Not valid format of date.");
        }
        news.setNewsDate(localDateTime);
        return news;
    }
}