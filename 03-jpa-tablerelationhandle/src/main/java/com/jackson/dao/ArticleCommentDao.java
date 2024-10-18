package com.jackson.dao;


import com.jackson.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleCommentDao extends JpaRepository<ArticleComment, Integer> , JpaSpecificationExecutor<ArticleComment> {
}
