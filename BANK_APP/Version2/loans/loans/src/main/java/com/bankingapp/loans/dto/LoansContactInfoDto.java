package com.bankingapp.loans.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loan")
public record LoansContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {

}
