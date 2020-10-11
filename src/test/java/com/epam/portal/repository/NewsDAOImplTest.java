package com.epam.portal.repository;

import com.epam.portal.entity.News;
import junit.framework.TestCase;
import org.junit.Assert;

import java.time.LocalDateTime;

public class NewsDAOImplTest extends TestCase {

    private static final String TEST_TEXT = "Test content";
    private static final LocalDateTime TEST_DATE = LocalDateTime.of(2000, 01, 01, 0, 0);
    private static final News testNews = new News(TEST_TEXT, TEST_TEXT,TEST_TEXT, TEST_DATE);
    private static final NewsDAO newsDAO = new NewsDAOImpl();

    public void testGetNewsById() {
        long id = newsDAO.saveOrUpdateNews(testNews);
        testNews.setId(id);
        News tempNews = newsDAO.getNewsById(id);
        Assert.assertEquals(testNews, tempNews);
    }

    public void testSaveOrUpdateNews() {
    }

    public void testGetAllNews() {
    }

    public void testDeleteNews() {
    }
}