package com.eazybytes.cards.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {

    @NotEmpty(message = "Please include a mobile number")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Pattern(regexp = "^[0-9]{12}$", message = "Card number must be 12 digits")
    @NotEmpty(message = "Please include a card number")
    private String cardNumber;

    @NotEmpty(message = "Please include a card type")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;
    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private int amountUsed;
    @PositiveOrZero(message = "Total available amount should be equal or greater than zero") //anotacion para validaciones de campos
    private int availableAmount;

}
