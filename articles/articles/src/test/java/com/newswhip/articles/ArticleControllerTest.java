package com.newswhip.articles;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newswhip.articles.dto.ArticleDto;
import com.newswhip.articles.dto.ResponseDto;
import com.newswhip.articles.entity.Article;
import com.newswhip.articles.service.ArticleService;

//@WebMvcTest(ArticleController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArticleService articleService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testCreateArticle() throws Exception {
		ArticleDto articleDto = new ArticleDto("https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/", 30,
				"ie");
		Article article = new Article(100, "https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/", 30,
				"ie");

		Mockito.when(articleService.createArticle(Mockito.any(ArticleDto.class))).thenReturn(article);

		mockMvc.perform(post("/article").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(articleDto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty()).andExpect(jsonPath("$.countryCode").isNotEmpty())
				.andExpect(jsonPath("$.url").value("https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/"))
				.andExpect(jsonPath("$.countryCode").value("ie"));
	}

	@Test
	void testDeleteArticleByUrl_Success() throws Exception {
		String validUrl = "https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/";
		when(articleService.deleteArticleByUrl(validUrl)).thenReturn("Article deleted successfully");

		mockMvc.perform(delete("/article").param("url", validUrl)).andExpect(status().isOk())
				.andExpect(content().string("Article deleted successfully"));
	}

	@Test

	void testDeleteArticleByUrl_InvalidUrl() throws Exception {
		mockMvc.perform(delete("/article").param("url", "ww.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/"))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
				.andExpect(jsonPath("$.errorMssage").value("invalid url"));
	}

	@Test
	void testDeleteArticleByUrl_ArticleNotFound() throws Exception {
		String validUrl = "https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/";
		when(articleService.deleteArticleByUrl(validUrl)).thenThrow(new RuntimeException("Article not found"));

		mockMvc.perform(delete("/article").param("url", validUrl)).andExpect(status().isBadRequest())
				.andExpect(content().string("Article not found"));
	}

	@Test
	void testGetArticleByCountrycode_ValidCountryCode() throws Exception {
		List<ResponseDto> mockResponse = Arrays.asList(new ResponseDto("rte.ie", 2, "80"));
		when(articleService.getArticleByCountrycode("ie")).thenReturn(mockResponse);

		mockMvc.perform(get("/report/ie")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(1))
				.andExpect(jsonPath("$[0].domain").value("rte.ie")).andExpect(jsonPath("$[0].urls").value(2))
				.andExpect(jsonPath("$[0].agg_social_score").value(80));
	}

	@Test
	void testGetArticleByCountrycode_EmptyCountryCode_ShouldReturnNotFound() throws Exception {
		mockMvc.perform(get("/report/")).andExpect(status().isNotFound());
	}

	@Test
	void testGetArticleByCountrycode_InvalidCountryCode_ShouldReturnBadRequest() throws Exception {
		mockMvc.perform(get("/report/123")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/report/i#$")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/report/iertcsd")).andExpect(status().isBadRequest());
	}

}
