package com.saitejaswar.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = " Schema to hold Customer and Account Information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example = " Eazy Bytes"
    )
    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 3, max = 30, message = " The length of the Customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "sai@eazybytes.com"
    )
    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "123456789"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
