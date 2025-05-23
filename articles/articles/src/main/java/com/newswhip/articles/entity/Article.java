package com.newswhip.articles.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="article")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Article extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // For auto-incrementing
	private int id;
	
	private String url;
	
	@Column(name = "social_score")
	private int socialScore;
	
	@Column(name = "country_code")
	private String countryCode;

}
