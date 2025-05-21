package com.springboot.chatbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.chatbot.dto.PromptRequest;
import com.springboot.chatbot.service.ChatbotService;

@RestController
@RequestMapping("/api")
public class ChatbotController {

	private ChatbotService chatbotService;

	public ChatbotController(ChatbotService chatbotService) {
		this.chatbotService = chatbotService;
	}

	@PostMapping("/chat")
	public String doChat(PromptRequest promptRequest) {
		return chatbotService.getChatResponse(promptRequest);
	}

}
