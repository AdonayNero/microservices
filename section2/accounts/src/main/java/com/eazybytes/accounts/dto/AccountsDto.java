package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * DTO que representa los datos de una cuenta.
 */
@Schema(
        name = "Accounts",
        description = "Shema to hold Account details"
)
@Data
public class AccountsDto {

    /**
     * Número de cuenta del cliente.
     */
    @Schema(
            description = "number of the customer",
            example = "1234567895"
    )
    @NotEmpty(message = "Account ID cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account ID must be 10 digits")
    private Long accountNumber;

    /**
     * Tipo de la cuenta.
     */
    @Schema(
            description = "Type of the account",
            example = "Savings"
    )
    @NotEmpty(message = "Account Type  cannot be empty")
    private String accountType;

    /**
     * Dirección de la sucursal del cliente.
     */
    @Schema(
            description = "Branch address of the customer",
            example = "123 New York"
    )
    @NotEmpty(message = "Branch ID cannot be empty")
    private String branchAddress;

}
