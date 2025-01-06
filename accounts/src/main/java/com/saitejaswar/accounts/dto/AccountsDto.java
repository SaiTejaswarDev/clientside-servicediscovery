package com.saitejaswar.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = " Schema to hold Account Information"
)
public class AccountsDto {

    @Schema(
            description = "Account Number of Eazy Bank Account",
            example = "3456781290"
    )
    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of Eazy Bank Account",
            example = "Checking"
    )
    @NotEmpty(message = "Account Type can not be null or empty")
    private String accountType;

    @Schema(
            description = "Eazy Bank branch address",
            example = "123 New York"
    )
    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;

}
