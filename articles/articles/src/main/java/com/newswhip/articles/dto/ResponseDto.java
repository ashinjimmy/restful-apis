package com.newswhip.articles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

	private String domain;
	private int urls;
	private String agg_social_score;
	
}
