package com.bankingapp.accounts.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class Base {

	@Column(updatable = false)
	private String createdBy;
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@Column(insertable = false)
	private String updatedBy;
	@Column(insertable = false)
	private LocalDateTime updateAt;

}
