package com.bankingapp.accounts.service;

import com.bankingapp.accounts.dto.CustomerDto;

public interface AccountsService {
	
	public void createAccount(CustomerDto customerDto);

	public CustomerDto fetchAccountInfo(String mobileNumber);

}
