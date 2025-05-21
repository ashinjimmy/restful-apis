package com.springboot.chatbot.dto;

import java.util.List;

public record ChatbotResponse(List<Choice> choices) {
	
	public static record Choice(Message message) {
		
		public static record Message(String role, String content) {}
	}

}
