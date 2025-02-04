package com.newswhip.articles.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newswhip.articles.dto.ArticleDto;
import com.newswhip.articles.dto.ResponseDto;
import com.newswhip.articles.entity.Article;
import com.newswhip.articles.service.ArticleService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
public class ArticleController {

	private ArticleService service;

	@PostMapping("/article")
	public ResponseEntity<Article> createArticle(@Valid @RequestBody ArticleDto articleDto) {
		Article response = service.createArticle(articleDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// http://localhost:6060/article?url=...
	@DeleteMapping("/article")
	public ResponseEntity<String> deleteArticleByUrl(
			@Pattern(regexp = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)", message = "invalid url")
			@RequestParam String url) {

		try {
			return ResponseEntity.ok(service.deleteArticleByUrl(url));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	
	// http://localhost:6060/report/ie
		@GetMapping("/report/{countrycode}")
		public ResponseEntity<List<ResponseDto>> getArticleByCountrycode(
				@NotEmpty(message = "cannot be empty or null")
				@Pattern(regexp = "^[a-zA-Z]{2,4}$", message = "invalid country code")
				@PathVariable("countrycode") String countryCode) {
			List<ResponseDto> response = service.getArticleByCountrycode(countryCode);
			 return ResponseEntity.status(HttpStatus.OK).body(response);
		}

}
