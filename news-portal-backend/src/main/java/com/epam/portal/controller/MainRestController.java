package com.epam.portal.controller;

import com.epam.portal.dto.DeleteNewsRequest;
import com.epam.portal.dto.NewsDTO;
import com.epam.portal.exception.BusinessException;
import com.epam.portal.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class MainRestController {


    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(BusinessException businessException) {
        return businessException.getMessage();
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException2(NullPointerException businessException) {
        return businessException.getMessage();
    }
    private NewsService newsService;

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public ResponseEntity<List<NewsDTO>> getAllNews() {

        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable("id") Long id) {

        // TODO catch exception if try to get non existing entity

        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @PostMapping(path = "/news", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrUpdateNews(@RequestBody NewsDTO newsDTO) throws BusinessException {

        newsService.saveOrUpdateNews(newsDTO);
        return ResponseEntity.ok(newsDTO.getTitle() + " " + newsDTO.getBrief());
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<String> deleteNewsById(@PathVariable("id") Long id) {

        // TODO catch exception if try to delete non existing entity

        newsService.deleteNews(id);
        return ResponseEntity.ok("News with id " + id + " deleted.");
    }

    @DeleteMapping(path = "/news", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteNews(@RequestBody DeleteNewsRequest body) {

        // TODO catch exception if try to delete non existing entity

        newsService.deleteNews(body.getNewsIdList());
        return ResponseEntity.ok("News with ids " + body.getNewsIdList().toString()  + " deleted.");
    }
}