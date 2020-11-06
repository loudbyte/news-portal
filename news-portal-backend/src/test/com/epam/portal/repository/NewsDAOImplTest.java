package com.epam.portal.repository;

import com.epam.portal.entity.News;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.rules.ExpectedException;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static com.epam.portal.NewsTestData.FORMATTER;
import static com.epam.portal.NewsTestData.TEST_LANG_RU;
import static com.epam.portal.NewsTestData.TEST_STRING_DATE;
import static com.epam.portal.NewsTestData.TEST_TEXT;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsDAOImplTest extends TestCase {

    @Mock
    private NewsDAO newsDAO;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    public News news;
    public LocalDate testDate;

    @Before
    public void setUp() {
        testDate = LocalDate.parse(TEST_STRING_DATE, FORMATTER);
        news = new News(TEST_TEXT, TEST_TEXT, TEST_TEXT, TEST_LANG_RU, testDate);
        news.setId(1L);
    }

    @Test
    public void testSaveOrUpdateNews_WhenEverythingIsOk() {
        when(newsDAO.saveOrUpdateNews(news)).thenReturn(news);
        News resultNews = newsDAO.saveOrUpdateNews(news);
        Assert.assertEquals(resultNews, news);
    }

    @Test
    public void testSaveOrUpdateNews_WhenThrowsBusinessException() {
        exceptionRule.expect(PersistenceException.class);
        DateTimeFormatter wrongFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDate wrongFormatDate = LocalDate.now();
        wrongFormatDate.format(wrongFormatter);
        news.setNewsDate(wrongFormatDate);
        when(newsDAO.saveOrUpdateNews(news)).thenThrow(new PersistenceException());
        newsDAO.saveOrUpdateNews(news);
    }

    @Test
    public void testGetAllNews_WhenEverythingIsOk() {
        List<News> testNewsList = Collections.emptyList();
        when(newsDAO.getAllNews()).thenReturn(testNewsList);
        List<News> resultList = newsDAO.getAllNews();
        Assert.assertEquals(testNewsList, resultList);
    }

    @Test
    public void testDeleteNews_WhenEverythingIsOk() {
        long testId = 1L;
        doNothing().when(newsDAO).deleteNews(testId);
        newsDAO.deleteNews(testId);
    }

    @Test
    public void testDeleteNews_WhenThrowsNPE() {
        long testId = 1L;
        exceptionRule.expect(NullPointerException.class);
        doThrow(new NullPointerException()).when(newsDAO).deleteNews(testId);
        newsDAO.deleteNews(testId);
    }

    @Test
    public void testGetNewsById_WhenEverythingIsOk() {
        long testId = 1L;
        when(newsDAO.getNewsById(testId)).thenReturn(news);
        News result = newsDAO.getNewsById(testId);
        Assert.assertEquals(testId, result.getId());
    }
}
