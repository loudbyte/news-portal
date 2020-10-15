package com.epam.portal.controller;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api")
@RestController
public class MainRestController {

    private NewsService newsService;

    @GetMapping("get-all-news")
    public ResponseEntity<List<NewsDTO>> getAllNews() {

        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("get-news-by-id")
    public ResponseEntity<NewsDTO> getNewsById(@RequestBody NewsDTO newsDTO){

        // TODO catch exception if try to delete non existing entity

        return ResponseEntity.ok(newsService.getNewsById(newsDTO.getId()));
    }

    @PostMapping(name = "save-or-update-news", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrUpdateNews(@RequestBody NewsDTO newsDTO) {

        newsService.saveOrUpdateNews(newsDTO);
        return ResponseEntity.ok(newsDTO.getTitle() + " " + newsDTO.getBrief());
    }

    @DeleteMapping("delete-news")
    public ResponseEntity<String> deleteNewsById(@RequestBody NewsDTO newsDTO) {

        // TODO catch exception if try to delete non existing entity

        newsService.deleteNews(newsDTO.getId());
        return ResponseEntity.ok("News with id " + newsDTO.getId() + " deleted.");
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}