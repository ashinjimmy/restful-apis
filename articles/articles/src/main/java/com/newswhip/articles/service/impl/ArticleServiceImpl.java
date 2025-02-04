package com.newswhip.articles.service.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newswhip.articles.dto.ArticleDto;
import com.newswhip.articles.dto.ResponseDto;
import com.newswhip.articles.entity.Article;
import com.newswhip.articles.exceptions.ArticleAlreadyExistException;
import com.newswhip.articles.exceptions.ResourceNotFoundException;
import com.newswhip.articles.mapper.ArticleMapper;
import com.newswhip.articles.repository.ArticleRepository;
import com.newswhip.articles.service.ArticleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private ArticleRepository repository;

	@Override
	public Article createArticle(ArticleDto articleDto) {
		Article articleResponse = null;

		Article article = ArticleMapper.mapArticleDtoToArticle(articleDto, new Article());
		Optional<Article> optionalArticle = repository.findByUrl(article.getUrl());

		if (optionalArticle.isPresent()) {
			throw new ArticleAlreadyExistException("Article already registered!!");
		}
		articleResponse = repository.save(article);
		return articleResponse;
	}
	
	

	@Override
	@Transactional
	public String deleteArticleByUrl(String url) {

		Optional<Article> article = repository.findByUrl(url);
		if (article.isPresent()) {
			repository.deleteByUrl(url);
		} else {
			throw new ResourceNotFoundException("Resource Not Found for the URL : " + url);
		}
		return "Article deleted successfully !!!";
	}

	@Override
	public List<ResponseDto> getArticleByCountrycode(String countryCode) {

		List<Article> articles = repository.findByCountryCode(countryCode);
		Map<String, ResponseDto> responseMap = new HashMap<>();

		if (articles != null && !articles.isEmpty()) {

			for (Article article : articles) {
				try {
					URL url = new URL(article.getUrl());
					String host = url.getHost();
					String domain = host.startsWith("www.") ? host.substring(4) : host;

					responseMap.putIfAbsent(domain, new ResponseDto());
					System.out.println(responseMap);
					ResponseDto dto = responseMap.get(domain);

					dto.setDomain(domain);
					dto.setUrls(dto.getUrls() + 1);
					dto.setAgg_social_score(String.valueOf(
							Integer.parseInt(dto.getAgg_social_score() == null ? "0" : dto.getAgg_social_score())
									+ article.getSocialScore()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new ResourceNotFoundException("Resource Not Found for the Country Code : " + countryCode);
		}

		List<ResponseDto> responseList = new ArrayList<>(responseMap.values());
		System.out.println(responseMap.values());
		// Sort the list by aggregated social score in descending order
		Collections.sort(responseList, (dto1, dto2) -> {
			int score1 = Integer.parseInt(dto1.getAgg_social_score() == null ? "0" : dto1.getAgg_social_score());
			int score2 = Integer.parseInt(dto2.getAgg_social_score() == null ? "0" : dto2.getAgg_social_score());
			return Integer.compare(score2, score1); // Descending order
		});

		return responseList;
	}

}
