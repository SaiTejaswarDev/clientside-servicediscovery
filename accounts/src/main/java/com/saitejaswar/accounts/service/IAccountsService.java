package com.saitejaswar.accounts.service;

import com.saitejaswar.accounts.dto.CustomerDto;

public interface IAccountsService {


    /**
     *
     * @param customerDto - CustomerDtoObject
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber to search for a contact
     * @return the Customer details with that mobile number
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the Account Details are successfully updated
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);

}
