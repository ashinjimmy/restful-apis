package com.newswhip.articles.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity {
	
	
	@CreatedBy
	@Column(updatable = false, name = "created_by")
	private String createdBy; 
	
	
	@CreatedDate
	@Column(updatable = false, name = "created_at")
	private LocalDateTime createdAt;
	
	
	

}
