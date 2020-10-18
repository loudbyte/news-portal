package com.epam.portal.service;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.entity.News;
import com.epam.portal.repository.NewsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsDAO newsDAO;

    @Override
    public long saveOrUpdateNews(NewsDTO news) {
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
        newsDTO.setNewsDate(news.getNewsDate());
        return newsDTO;
    }

    private News convertNewsDTOToEntity(NewsDTO newsDTO) {
        News news = new News();
        news.setId(newsDTO.getId());
        news.setTitle(newsDTO.getTitle());
        news.setBrief(newsDTO.getBrief());
        news.setContent(newsDTO.getContent());
        news.setNewsDate(newsDTO.getNewsDate());
        return news;
    }
}