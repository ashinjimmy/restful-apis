package com.bankingapp.accounts.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Base {

	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@LastModifiedBy
	@Column(insertable = false)
	private String updatedBy;
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime updateAt;

}
