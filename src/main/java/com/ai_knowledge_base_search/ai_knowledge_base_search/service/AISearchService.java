package com.ai_knowledge_base_search.ai_knowledge_base_search.service;

import com.ai_knowledge_base_search.ai_knowledge_base_search.dto.request.ArticleRequest;
import com.ai_knowledge_base_search.ai_knowledge_base_search.dto.response.SearchResponse;
import com.ai_knowledge_base_search.ai_knowledge_base_search.exception.SystemException;
import com.ai_knowledge_base_search.ai_knowledge_base_search.model.Article;
import com.ai_knowledge_base_search.ai_knowledge_base_search.repository.ArticleRepository;
import com.ai_knowledge_base_search.ai_knowledge_base_search.util.AISimulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AISearchService {
    private final ArticleRepository articleRepository;
    private final AISimulator aiSimulator; // AI simulator to process search queries and summarize results

    public AISearchService(ArticleRepository articleRepository, AISimulator aiSimulator) {
        this.articleRepository = articleRepository;
        this.aiSimulator = aiSimulator;
    }
    public SearchResponse searchQuery(String query) {
        log.info("Processing search query: {}", query);

        if(query == null || query.trim().isEmpty()) {
            log.error("Query is null or empty");
            throw new IllegalArgumentException("Query cannot be null or empty");
        }

        List<Article> relevantArticles;
        String aiSummaryAnswer = "";

        try {
            List<Article> allArticles = articleRepository.findAll();
            log.info("Fetched {} articles from the repository", allArticles.size());

            if (allArticles.isEmpty()) {
                log.warn("No articles found in the repository");
                return SearchResponse.builder()
                        .articles(new ArrayList<>())
                        .aiSummaryAnswer("No articles found")
                        .build();
            }
            relevantArticles = aiSimulator.getRelevantArticles(allArticles, query);
            aiSummaryAnswer = aiSimulator.getAiSummaryAnswer(relevantArticles, query);

            log.info("Found {} relevant articles", relevantArticles.size());
        }catch (Exception e) {
            log.error("Error processing AI search query: {}", e.getMessage(), e);
            throw new SystemException("Error processing AI search query: " + e.getMessage(), e);
        }

        return SearchResponse.builder()
                .articles(relevantArticles)
                .aiSummaryAnswer(aiSummaryAnswer)
                .build();

    }

    private Article convertToArticle(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setUrl(articleRequest.getUrl());
        article.setSource(articleRequest.getSource());
        return article;
    }

    public String createArticle(ArticleRequest articleRequest) {
        log.info("Creating article with title: {}", articleRequest.getTitle());

        if(articleRequest.getTitle() == null || articleRequest.getContent() == null) {
            log.error("Article title or content is null");
            throw new IllegalArgumentException("Article title and content cannot be null");
        }

        Article article = convertToArticle(articleRequest);

        try {
            articleRepository.save(article);
            log.info("Article created successfully with ID: {}", article.getId());
            return "Article created successfully with ID: " + article.getId();
        } catch (Exception e) {
            log.error("Error creating article: {}", e.getMessage(), e);
            throw new SystemException("Error creating article: " + e.getMessage(), e);
        }

    }
}
