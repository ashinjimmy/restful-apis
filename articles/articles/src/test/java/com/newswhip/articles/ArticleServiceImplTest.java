package com.newswhip.articles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.newswhip.articles.dto.ArticleDto;
import com.newswhip.articles.dto.ResponseDto;
import com.newswhip.articles.entity.Article;
import com.newswhip.articles.exceptions.ArticleAlreadyExistException;
import com.newswhip.articles.exceptions.ResourceNotFoundException;
import com.newswhip.articles.repository.ArticleRepository;
import com.newswhip.articles.service.impl.ArticleServiceImpl;

@SpringBootTest
public class ArticleServiceImplTest {

	@InjectMocks
	private ArticleServiceImpl articleService;

	@Mock
	private ArticleRepository repository;

	@BeforeEach
	void setUp() {
		// MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateArticle_Success() {
		ArticleDto articleDto = new ArticleDto("http://www.rte.ie", 30, "ie");
		Article article = new Article(100, "http://www.rte.ie", 30, "ie");

		Mockito.when(repository.findByUrl(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(repository.save(Mockito.any(Article.class))).thenReturn(article);

		Article result = articleService.createArticle(articleDto);

		assertNotNull(result);
		assertEquals(100, result.getId());
		assertEquals("http://www.rte.ie", result.getUrl());
	}

	@Test
	void testCreateArticle_AlreadyExists() {
		ArticleDto articleDto = new ArticleDto("http://www.rte.ie", 30, "ie");
		Article article = new Article(100, "http://www.rte.ie", 30, "ie");

		Mockito.when(repository.findByUrl(Mockito.anyString())).thenReturn(Optional.of(article));

		assertThrows(ArticleAlreadyExistException.class, () -> articleService.createArticle(articleDto));
	}

	@Test
	void testDeleteArticleByUrl_Success() {
		String url = "https://www.irishtimes.com/opinion/cartoon/2025/martyn-turner/";
		Article article = new Article(230, "https://www.irishtimes.com/opinion/cartoon/2025/martyn-turner/", 50, "ie");

		Mockito.when(repository.findByUrl(url)).thenReturn(Optional.of(article));
		doNothing().when(repository).deleteByUrl(url);

		String result = articleService.deleteArticleByUrl(url);

		assertEquals("Article deleted successfully !!!", result);
		Mockito.verify(repository, Mockito.times(1)).deleteByUrl(url);
	}

	@Test
	void testDeleteArticleByUrl_NotFound() {
		String url = "https://www.irishtimes.com/opinion/cartoon/2025/martyn-turner/";

		Mockito.when(repository.findByUrl(url)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> articleService.deleteArticleByUrl(url));
	}

	@Test
	void testGetArticleByCountryCode_Success() {
		String countryCode = "ie";
		List<Article> articles = Arrays.asList(
				new Article(20, "http://www.rte.ie/news/politics/2018/1004/1001034-cso/", 60, "ie"),
				new Article(35, "https://elpais.com/cultura/festival-de-musica-de-canaria", 10, "es"),
				new Article(10, "https://www.irishtimes.com/opinion/cartoon/2025/martyn-turner/", 50, "ie"),
				new Article(45, "https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/", 20, "ie"));

		Mockito.when(repository.findByCountryCode(countryCode)).thenReturn(articles);

		List<ResponseDto> response = articleService.getArticleByCountrycode(countryCode);

		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(3, response.size());
		assertEquals("rte.ie", response.get(0).getDomain());
		assertEquals("irishtimes.com", response.get(1).getDomain());
	}

	@Test
	void testGetArticleByCountryCode_NotFound() {
		String countryCode = "ie";

		Mockito.when(repository.findByCountryCode(countryCode)).thenReturn(Collections.emptyList());

		assertThrows(ResourceNotFoundException.class, () -> articleService.getArticleByCountrycode(countryCode));
	}

}
