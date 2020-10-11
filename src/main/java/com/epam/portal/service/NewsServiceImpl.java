package com.epam.portal.service;

import com.epam.portal.entity.News;
import com.epam.portal.repository.NewsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsDAO newsDAO;

    @Override
    public long saveOrUpdateNews(News news) {
        return newsDAO.saveOrUpdateNews(news);
    }

    @Override
    public News getNewsById(long id) {
        return newsDAO.getNewsById(id);
    }

    @Override
    public List<News> getAllNews() {
        return newsDAO.getAllNews();
    }

    @Override
    public void deleteNews(News news) {
        newsDAO.deleteNews(news);
    }

    @Autowired
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}