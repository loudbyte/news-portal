package com.epam.portal.service;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.entity.News;
import com.epam.portal.exception.BusinessException;
import com.epam.portal.repository.NewsDAO;
import com.epam.portal.validation.NewsDTOTextLengthValidator;
import com.epam.portal.validation.TextForbiddenWordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsDAO newsDAO;

    @Override
    public NewsDTO saveOrUpdateNews(NewsDTO newsDTO) throws BusinessException {
        isValid(newsDTO);
        return convertNewsEntityToDTO(newsDAO.saveOrUpdateNews(convertNewsDTOToEntity(newsDTO)));
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(newsDTO.getNewsDate(), formatter);
        } catch (Exception exception) {
            throw new BusinessException("Not valid format of date");
        }
        news.setNewsDate(localDate);
        return news;
    }

    private void isValid(NewsDTO newsDTO) throws BusinessException {
        TextForbiddenWordValidator.isNotContainsForbiddenWords(newsDTO.getTitle());
        TextForbiddenWordValidator.isNotContainsForbiddenWords(newsDTO.getBrief());
        TextForbiddenWordValidator.isNotContainsForbiddenWords(newsDTO.getContent());
        NewsDTOTextLengthValidator.isTitleLengthValid(newsDTO);
        NewsDTOTextLengthValidator.isBriefLengthValid(newsDTO);
        NewsDTOTextLengthValidator.isContentLengthValid(newsDTO);
        NewsDTOTextLengthValidator.isDateLengthValid(newsDTO);
    }
}