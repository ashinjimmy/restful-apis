package com.newswhip.articles.service;

import java.util.List;

import com.newswhip.articles.dto.ArticleDto;
import com.newswhip.articles.dto.ResponseDto;
import com.newswhip.articles.entity.Article;

public interface ArticleService {

	Article createArticle(ArticleDto articleDto);

	String deleteArticleByUrl(String url);

	List<ResponseDto> getArticleByCountrycode(String countryCode);

}
