package com.ai_knowledge_base_search.ai_knowledge_base_search.dto.response;

import com.ai_knowledge_base_search.ai_knowledge_base_search.model.Article;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class SearchResponse {

    @JsonProperty("relevant_articles")
    private List<Article> articles;

    @JsonProperty("ai_summary_answer")
    private String aiSummaryAnswer;


}
