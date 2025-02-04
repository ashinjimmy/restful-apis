package com.newswhip.articles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class ArticlesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticlesApplication.class, args);
	}

}
