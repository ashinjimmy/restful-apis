package com.newswhip.articles.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

	@NotEmpty(message = "cannot be null or empty")
	@Pattern(regexp = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)", message = "invalid url")
	private String url;
	
	@Positive
	private Integer socialScore;
	
	@NotEmpty(message = "cannot be null or empty")
	@Pattern(regexp = "^[a-zA-Z]{2,4}$", message = "invalid country code")
	private String countryCode;

}
