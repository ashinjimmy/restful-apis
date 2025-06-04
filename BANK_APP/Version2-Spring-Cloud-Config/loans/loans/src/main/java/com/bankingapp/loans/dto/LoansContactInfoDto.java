package com.bankingapp.loans.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "loan")
@Data
public class LoansContactInfoDto {
	private String message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;

}
