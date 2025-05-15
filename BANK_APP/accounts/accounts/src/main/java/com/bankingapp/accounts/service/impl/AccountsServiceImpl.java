package com.bankingapp.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bankingapp.accounts.constants.AccountsConstants;
import com.bankingapp.accounts.dto.AccountsDto;
import com.bankingapp.accounts.dto.CustomerDto;
import com.bankingapp.accounts.entity.Accounts;
import com.bankingapp.accounts.entity.Customer;
import com.bankingapp.accounts.exception.CustomerAlreadyExistsException;
import com.bankingapp.accounts.exception.ResourceNotFoundException;
import com.bankingapp.accounts.mapper.AccountsMapper;
import com.bankingapp.accounts.mapper.CustomerMapper;
import com.bankingapp.accounts.repository.AccountsRepository;
import com.bankingapp.accounts.repository.CustomerRepository;
import com.bankingapp.accounts.service.AccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		System.out.println(customerDto);

		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> customerMobileNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if (customerMobileNumber.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer Already Exist with the given Mobile Number " + customerDto.getMobileNumber());
		}
		Customer savedCustomer = customerRepository.save(customer);
		accountsRepository.save(openAccount(savedCustomer));
	}

	private Accounts openAccount(Customer savedCustomer) {
		Accounts account = new Accounts();
		account.setCustomerId(savedCustomer.getCustomerId());
		Long accountNumber = 1000000000L + new Random().nextLong(9000000000L);
		account.setAccountNumber(accountNumber);
		account.setAccountType(AccountsConstants.SAVINGS);
		account.setBranchAddress(AccountsConstants.ADDRESS);
		return account;

	}

	@Override
	public CustomerDto fetchAccountInfo(String mobileNumber) {

		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber));

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		 boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (accountsDto != null) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
							accountsDto.getAccountNumber().toString()));
			AccountsMapper.mapToAccounts(accountsDto, accounts);
			accounts = accountsRepository.save(accounts);

			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));
			CustomerMapper.mapToCustomer(customerDto, customer);
			customerRepository.save(customer);
			isUpdated = true;
		}

		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
	}

}
