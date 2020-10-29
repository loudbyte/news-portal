package com.epam.portal.service;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.entity.News;
import com.epam.portal.exception.BusinessException;
import com.epam.portal.repository.NewsDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.epam.portal.NewsTestData.NEWS_ID_1;
import static com.epam.portal.NewsTestData.TEST_DATE_TIME;
import static com.epam.portal.NewsTestData.TEST_STRING_DATE;
import static com.epam.portal.NewsTestData.TEST_TEXT;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    @InjectMocks
    private NewsServiceImpl newsService;

    @Mock
    private NewsDAO newsDAO;

    public NewsDTO newsDTO;
    public News news;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void init() {
        newsDTO = new NewsDTO(NEWS_ID_1, TEST_TEXT, TEST_TEXT,TEST_TEXT, TEST_STRING_DATE);
        news = new News(TEST_TEXT, TEST_TEXT,TEST_TEXT, TEST_DATE_TIME);
        news.setId(1L);
    }

    @Test
    public void testSaveOrUpdateNews_WhenEverythingIsOk() throws BusinessException {
        when(newsDAO.saveOrUpdateNews(news)).thenReturn(news);
        NewsDTO resultNewsDTO = newsService.saveOrUpdateNews(newsDTO);
        Assert.assertEquals(resultNewsDTO, newsDTO);
    }

    @Test
    public void testSaveOrUpdateNews_WhenThrowsBusinessException() throws BusinessException {
        exceptionRule.expect(BusinessException.class);
        exceptionRule.expectMessage("Not valid format of date.");
        newsDTO.setNewsDate("wrong date format");
        newsService.saveOrUpdateNews(newsDTO);
    }

    @Test
    public void testGetNewsById_WhenEverythingIsOk() {
        long testId = 1L;
        when(newsDAO.getNewsById(testId)).thenReturn(news);
        NewsDTO resultNewsDTOFromService = newsService.getNewsById(testId);
        Assert.assertEquals(testId, resultNewsDTOFromService.getId());
    }

    @Test
    public void testGetNewsById_WhenThrowsNPE() {
        long testId = 1L;
        exceptionRule.expect(NullPointerException.class);
        when(newsDAO.getNewsById(testId)).thenThrow(new NullPointerException());
        newsService.getNewsById(testId);
    }

    @Test
    public void testGetAllNews_WhenEverythingIsOk() {
        List<News> testNewsList = Collections.emptyList();
        List<NewsDTO> expectedNewsDTOList = Collections.emptyList();
        when(newsDAO.getAllNews()).thenReturn(testNewsList);
        List<NewsDTO> resultList = newsService.getAllNews();
        Assert.assertEquals(expectedNewsDTOList, resultList);
    }

    @Test
    public void testDeleteNews_WhenEverythingIsOk() {
        long testId = 1L;
        doNothing().when(newsDAO).deleteNews(testId);
        newsService.deleteNews(testId);
    }

    @Test
    public void testDeleteNews_WhenThrowsNPE() {
        long testId = 1L;
        exceptionRule.expect(NullPointerException.class);
        doThrow(new NullPointerException()).when(newsDAO).deleteNews(testId);
        newsService.deleteNews(testId);
    }

    @Test
    public void testDeleteNewsList_WhenEverythingIsOk() {
        long testId = 1L;
        List<Long> testLongList = new ArrayList<>();
        testLongList.add(testId);
        doNothing().when(newsDAO).deleteNews(testId);
        newsService.deleteNews(testLongList);
    }

    @Test
    public void testDeleteNewsList_WhenNPE() {
        long testId = 1L;
        List<Long> testLongList = new ArrayList<>();
        testLongList.add(testId);
        exceptionRule.expect(NullPointerException.class);
        doThrow(new NullPointerException()).when(newsDAO).deleteNews(testId);
        newsService.deleteNews(testLongList);
    }

}