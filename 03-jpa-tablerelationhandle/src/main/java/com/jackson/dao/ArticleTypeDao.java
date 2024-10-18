package com.jackson.dao;

import com.jackson.domain.Article;
import com.jackson.domain.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleTypeDao extends JpaRepository<ArticleType, Integer>, JpaSpecificationExecutor<ArticleType> {
}
