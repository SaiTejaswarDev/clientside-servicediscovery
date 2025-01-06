package com.saitejaswar.accounts.service.impl;

import com.saitejaswar.accounts.dto.AccountsDto;
import com.saitejaswar.accounts.dto.CardsDto;
import com.saitejaswar.accounts.dto.CustomerDetailsDto;
import com.saitejaswar.accounts.dto.LoansDto;
import com.saitejaswar.accounts.entity.Accounts;
import com.saitejaswar.accounts.entity.Customer;
import com.saitejaswar.accounts.exception.ResourceNotFoundException;
import com.saitejaswar.accounts.mapper.AccountsMapper;
import com.saitejaswar.accounts.mapper.CustomerMapper;
import com.saitejaswar.accounts.repository.AccountRepository;
import com.saitejaswar.accounts.repository.CustomerRepository;
import com.saitejaswar.accounts.service.ICustomerService;
import com.saitejaswar.accounts.service.client.CardsFeignClient;
import com.saitejaswar.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given Mobile Number
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts= accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto= CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity= loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity= cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
