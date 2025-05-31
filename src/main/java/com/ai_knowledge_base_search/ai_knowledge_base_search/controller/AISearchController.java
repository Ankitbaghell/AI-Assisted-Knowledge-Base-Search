package com.ai_knowledge_base_search.ai_knowledge_base_search.controller;

import com.ai_knowledge_base_search.ai_knowledge_base_search.dto.request.ArticleRequest;
import com.ai_knowledge_base_search.ai_knowledge_base_search.dto.response.SearchResponse;
import com.ai_knowledge_base_search.ai_knowledge_base_search.service.AISearchService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class AISearchController {

    private final AISearchService aiSearchService;

    public AISearchController(AISearchService aiSearchService) {
        this.aiSearchService = aiSearchService;
    }

    @PostMapping("/search-query")
    public ResponseEntity<SearchResponse> searchQuery(@RequestParam("query") String query) {
        log.info("Received search query: {}", query);
        SearchResponse result = aiSearchService.searchQuery(query);
        log.info("Search response: {}", result);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/create-articles")
    public ResponseEntity<String> createArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        log.info("Received request to create article: {}", articleRequest);
        String result = aiSearchService.createArticle(articleRequest);
        log.info("Article created successfully");
        return ResponseEntity.ok(result);
    }
}