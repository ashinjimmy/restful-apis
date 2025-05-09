package com.bankingapp.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.accounts.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}
