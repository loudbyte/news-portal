package com.epam.portal.controller;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.exception.BusinessException;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.epam.portal.NewsTestData.NEWS_ID_1;
import static com.epam.portal.NewsTestData.NEWS_ID_2;
import static com.epam.portal.NewsTestData.TEST_STRING_DATE;
import static com.epam.portal.NewsTestData.TEST_TEXT;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    public NewsDTO anotherNewsDTO;
    List<NewsDTO> newsDTOList;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MainRestController(newsService)).build();
        newsDTO = new NewsDTO(NEWS_ID_1, TEST_TEXT, TEST_TEXT,TEST_TEXT, TEST_STRING_DATE);
        anotherNewsDTO =  new NewsDTO(NEWS_ID_2, TEST_TEXT, TEST_TEXT,TEST_TEXT, TEST_STRING_DATE);
        newsDTOList = new ArrayList<>();
        newsDTOList.add(newsDTO);
        newsDTOList.add(anotherNewsDTO);
    }

    @Test
    public void testGetAllNews_WhenEverythingIsOk() throws Exception {
        when(newsService.getAllNews()).thenReturn(Collections.singletonList(newsDTO));
        mockMvc.perform(get("/api/news"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(newsDTO.getId()))
                .andExpect(jsonPath("$.[0].title").value(newsDTO.getTitle()))
                .andExpect(jsonPath("$.[0].brief").value(newsDTO.getBrief()))
                .andExpect(jsonPath("$.[0].content").value(newsDTO.getContent()))
                .andExpect(jsonPath("$.[0].newsDate").value(newsDTO.getNewsDate()));
    }

    @Test
    public void testGetNewsById_WhenEverythingIsOkd() throws Exception {
        when(newsService.getNewsById(newsDTO.getId())).thenReturn(newsDTO);
        mockMvc.perform(get("/api/news/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(newsDTO.getId()))
                .andExpect(jsonPath("$.title").value(newsDTO.getTitle()))
                .andExpect(jsonPath("$.brief").value(newsDTO.getBrief()))
                .andExpect(jsonPath("$.newsDate").value(newsDTO.getNewsDate()));
    }

    @Test
    public void testGetNewsById_WhenThrowsNPE() throws Exception {
        when(newsService.getNewsById(NEWS_ID_1)).thenThrow(new NullPointerException());
        mockMvc.perform(get("/api/news/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testSaveOrUpdateNews_WhenEverythingIsOk() throws Exception {
        when(newsService.saveOrUpdateNews(newsDTO)).thenReturn(newsDTO);
        mockMvc.perform(post("/api/news")
                // TODO something with "{}"
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveOrUpdateNews_WhenReturnError415() throws Exception {
        when(newsService.saveOrUpdateNews(newsDTO)).thenReturn(newsDTO);
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

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAuthAdmin() throws Exception {
        mockMvc.perform(get("/api/news"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/news/1"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/news")
                // TODO something with "{}"
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/news/1"))
                .andExpect(status().isOk());

//        mockMvc.perform(delete("/api/news")
//                // TODO something with "{}"
//                .content("{}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());

    }

//    @Test
//    @WithMockUser(roles = "MANAGER")
//    public void testAuthManager() throws Exception {
//
//        mockMvc.perform(get("/api/news"))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/api/news/1"))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(post("/api/news")
//                // TODO something with "{}"
//                .content("{}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(delete("/api/news/1"))
//                .andExpect(status().is(403));
//
////        mockMvc.perform(delete("/api/news")
////                // TODO something with "{}"
////                .content("{}")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk());
//
//    }
//
//    @Test
//    @WithMockUser(roles = "EMPLOYEE")
//    public void testAuthEmployee() throws Exception {
//        mockMvc.perform(get("/api/news")
//                .with(SecurityMockMvcRequestPostProcessors.user("admin").password("pass").roles("ADMIN")))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/api/news/1"))
//                .andExpect(status().isOk());
//
////        mockMvc.perform(post("/api/news")
////                // TODO something with "{}"
////                .content("{}")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().is(403));
//
//        mockMvc.perform(delete("/api/news/1")
//                .with(SecurityMockMvcRequestPostProcessors.user("admin").password("pass").roles("EMPLOYEE")))
//                .andExpect(status().is(403));
//
////        mockMvc.perform(delete("/api/news")
////                // TODO something with "{}"
////                .content("{}")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk());
//
//    }

}