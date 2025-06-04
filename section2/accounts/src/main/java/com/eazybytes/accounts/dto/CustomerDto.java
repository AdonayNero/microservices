package com.eazybytes.accounts.dto;


import com.eazybytes.accounts.entity.Accounts;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO que agrupa la información de un cliente y sus cuentas.
 */
@Data
@Schema(
        name = "Customer",
        description = "Shema to hold Customer and Account details"
)
public class CustomerDto {

    /**
     * Nombre del cliente.
     */
    @Schema(
            description = "Name of the customer",
            example = "Elon Musk"
    )
    @NotEmpty(message = "Customer ID cannot be empty")
    @Size(min = 5, max = 30, message = "Customer ID must be between 5 and 30 characters")
    private String name;

    /**
     * Correo electrónico del cliente.
     */
    @Schema(
            description = "Email of the customer",
            example = "elon.musk@spacex"
    )
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "email should be valid")
    private String email;


    /**
     * Número de teléfono del cliente.
     */
    @Schema(
            description = "Mobile number of the customer",
            example = "1234567895"
    )
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    /**
     * Datos de la cuenta asociados al cliente.
     */
    @Schema(
            description = "Account details of the customer"

    )
    private AccountsDto accountsDto;

}
