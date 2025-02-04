package com.newswhip.articles.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newswhip.articles.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	Optional<Article> findByUrl(String url);

	void deleteByUrl(String url);

	List<Article> findByCountryCode(String countryCode);
}
