package com.epam.portal.controller;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class MainRestController {

    private NewsService newsService;

    @GetMapping("/get-all-news")
    public ResponseEntity<List<NewsDTO>> getAllNews() {

        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/get-news-by-id")
    public ResponseEntity<NewsDTO> getNewsById(@RequestBody NewsDTO newsDTO){

        // TODO catch exception if try to get non existing entity

        return ResponseEntity.ok(newsService.getNewsById(newsDTO.getId()));
    }

    @PostMapping(path = "/save-or-update-news", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrUpdateNews(@RequestBody NewsDTO newsDTO) {

        newsService.saveOrUpdateNews(newsDTO);
        return ResponseEntity.ok(newsDTO.getTitle() + " " + newsDTO.getBrief());
    }

    @DeleteMapping("/delete-news/{id}")
    public ResponseEntity<String> deleteNewsById(@PathVariable("id") Long id) {

        // TODO catch exception if try to delete non existing entity

        newsService.deleteNews(id);
        return ResponseEntity.ok("News with id " + id + " deleted.");
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

}