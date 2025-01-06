package com.saitejaswar.accounts.service;

import com.saitejaswar.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
