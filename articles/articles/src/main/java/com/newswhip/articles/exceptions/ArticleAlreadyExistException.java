package com.newswhip.articles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ArticleAlreadyExistException extends RuntimeException {

	public ArticleAlreadyExistException(String message) {
		super(message);
	}

}
