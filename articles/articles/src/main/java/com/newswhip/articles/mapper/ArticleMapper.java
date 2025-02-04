package com.newswhip.articles.mapper;

import com.newswhip.articles.dto.ArticleDto;
import com.newswhip.articles.entity.Article;

public class ArticleMapper {

	public static Article mapArticleDtoToArticle(ArticleDto articleDto, Article article) {

		article.setCountryCode(articleDto.getCountryCode());
		article.setUrl(articleDto.getUrl());
		article.setSocialScore(articleDto.getSocialScore());
		return article;
	}

}
