package com.epam.portal.controller;

import com.epam.portal.entity.News;
import com.epam.portal.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private NewsService newsService;

    @GetMapping("/get-all-news")
    public ResponseEntity<List<News>> getAllNews() {

        return ResponseEntity.ok(newsService.getAllNews());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrUpdateNews(@RequestBody News news) {

        newsService.saveOrUpdateNews(news);
        return ResponseEntity.ok(news.getTitle() + " " + news.getBrief());
    }

}