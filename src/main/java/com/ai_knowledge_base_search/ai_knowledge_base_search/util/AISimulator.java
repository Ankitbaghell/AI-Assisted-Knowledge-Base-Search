package com.ai_knowledge_base_search.ai_knowledge_base_search.util;

import com.ai_knowledge_base_search.ai_knowledge_base_search.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AISimulator {

    public List<Article> getRelevantArticles(List<Article> allArticles, String query) {
        log.info("Filtering relevant articles for query: {}", query);
        return allArticles.stream()
                .filter(article -> article.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        article.getContent().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public String getAiSummaryAnswer(List<Article> relevantArticles, String query) {
        log.info("Generating AI summary for query: {}", query);

        if (relevantArticles.isEmpty()) {
            log.warn("No relevant articles found for query: {}", query);
            return "No relevant articles found for the query: " + query;
        }

        List<String> titles = relevantArticles.stream()
                .map(Article::getTitle)
                .collect(Collectors.toList());

        String summary = "Summary of relevant articles: " + String.join(", ", titles);

        log.info("Generated AI summary successfully");
        return summary.toString();
    }
}