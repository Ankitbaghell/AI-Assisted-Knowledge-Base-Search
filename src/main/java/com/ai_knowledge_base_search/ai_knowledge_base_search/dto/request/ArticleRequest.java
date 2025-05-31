package com.ai_knowledge_base_search.ai_knowledge_base_search.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ArticleRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @Size(max = 2083, message = "URL cannot exceed 2083 characters")
    @URL(message = "Invalid URL format")
    private String url;

    @Size(max = 255, message = "Source cannot exceed 255 characters")
    private String source;
}
