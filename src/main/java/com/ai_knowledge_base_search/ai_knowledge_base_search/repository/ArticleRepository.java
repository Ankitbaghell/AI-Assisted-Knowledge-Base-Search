package com.ai_knowledge_base_search.ai_knowledge_base_search.repository;

import com.ai_knowledge_base_search.ai_knowledge_base_search.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
