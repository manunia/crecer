package com.student.crecer.repositories;

import com.student.crecer.domain.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepo extends CrudRepository<Article, Long> {

    List<Article> findByTag(String tag);
}
