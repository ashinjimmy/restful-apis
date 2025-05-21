package com.springboot.chatbot.dto;

import java.util.List;

public record ChatbotRequest(String model, List<Message> message) {
	
	public static record Message (String role, String content) {}

}
