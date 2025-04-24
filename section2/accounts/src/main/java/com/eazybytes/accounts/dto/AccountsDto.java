package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account ID cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account ID must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type  cannot be empty")
    private String accountType;

    @NotEmpty(message = "Branch ID cannot be empty")
    private String branchAddress;

}
