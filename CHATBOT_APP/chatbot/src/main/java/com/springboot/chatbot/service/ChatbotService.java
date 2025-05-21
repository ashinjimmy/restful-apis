package com.springboot.chatbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.springboot.chatbot.dto.ChatbotRequest;
import com.springboot.chatbot.dto.ChatbotResponse;
import com.springboot.chatbot.dto.PromptRequest;

@Service
public class ChatbotService {

	private final RestClient restClient;

	public ChatbotService(RestClient restClient) {
		this.restClient = restClient;
	}

	@Value("${openapi.api.key}")
	private String apiKey;

	@Value("${openapi.api.model}")
	private String model;

	public String getChatResponse(PromptRequest promptRequest) {

		ChatbotRequest chatbotRequest = new ChatbotRequest(model, List.of(new ChatbotRequest.Message("role", apiKey)));
		ChatbotResponse chatResponse = restClient.post()
				.header("Authorization", "Bearer " + apiKey)
				.header("Content-Type", "application/json")
				.body(chatbotRequest).retrieve().body(ChatbotResponse.class);
		
		return chatResponse.choices().get(0).message().content();

	}

}
