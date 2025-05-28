package com.bankingapp.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.bankingapp.accounts.dto.AccountsContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties( value = AccountsContactInfoDto.class)
@OpenAPIDefinition(info = @Info(title = "Accounts Microservice REST API Documentation", description = "BANK Accounts Microservice REST API Documentation", version = "v1", contact = @Contact(name = "Ashin Jimmy", email = "ashinjimmy54@gmail.com", url = "https://github.com/ashinjimmy"), license = @License(name = "Apache 2.0", url = "https://github.com/ashinjimmy")), externalDocs = @ExternalDocumentation(description = "BANK Accounts microservice REST API Documentation", url = "http://localhost:9091/swagger-ui/index.html"))
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
