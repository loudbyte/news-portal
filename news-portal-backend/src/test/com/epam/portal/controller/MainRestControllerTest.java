package com.epam.portal.controller;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.entity.News;
import com.epam.portal.exception.BusinessException;
import com.epam.portal.repository.NewsDAO;
import com.epam.portal.service.NewsService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.PersistenceException;
import java.util.Collections;

import static com.epam.portal.NewsTestData.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class MainRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NewsService newsService;

    @InjectMocks
    private MainRestController controller;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    public NewsDTO newsDTO;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MainRestController(newsService)).build();
        newsDTO = new NewsDTO(NEWS_ID_1, TEST_TEXT, TEST_TEXT,TEST_TEXT, TEST_STRING_DATE);
    }

    @Test
    public void testGetAllNews_WhenEverythingIsOk() throws Exception {
        when(newsService.getAllNews()).thenReturn(Collections.singletonList(newsDTO));
        mockMvc.perform(get("/api/news"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNewsByI_WhenEverythingIsOkd() throws Exception {
        long testId = 1L;
        when(newsService.getNewsById(testId)).thenReturn(newsDTO);
        mockMvc.perform(get("/api/news/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNewsById_WhenThrowsNPE() throws Exception {
        when(newsService.getNewsById(NEWS_ID_1)).thenThrow(new NullPointerException());
        mockMvc.perform(get("/api/news/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testSaveOrUpdateNews_WhenEverythingIsOk() throws Exception {
        when(newsService.saveOrUpdateNews(newsDTO)).thenReturn(NEWS_ID_1);
        mockMvc.perform(post("/api/news")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveOrUpdateNews_WhenReturnError415() throws Exception {
        long testId = 1L;
        when(newsService.saveOrUpdateNews(newsDTO)).thenReturn(testId);
        mockMvc.perform(post("/api/news")
                .content("")
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is(415));
    }

    @Test
    public void testSaveOrUpdateNews_WhenThrowsBusinessException() throws Exception {
        exceptionRule.expect(BusinessException.class);
        exceptionRule.expectMessage("Failed to create news");
        newsDTO.setNewsDate("wrong date format");
        when(newsService.saveOrUpdateNews(newsDTO)).thenThrow(new BusinessException("Failed to create news"));
        controller.saveOrUpdateNews(newsDTO);
    }

    @Test
    public void testDeleteNewsById_WhenEverythingIsOk() throws Exception {
        long testId = 1L;
        doNothing().when(newsService).deleteNews(testId);
        mockMvc.perform(delete("/api/news/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteNewsById_WhenThrowsBusinessException() throws Exception {
        long testId = 1L;
        exceptionRule.expect(BusinessException.class);
        exceptionRule.expectMessage("News with that id not found");
        when(controller.deleteNewsById(testId)).thenThrow(new PersistenceException());
        controller.deleteNewsById(testId);
    }

}