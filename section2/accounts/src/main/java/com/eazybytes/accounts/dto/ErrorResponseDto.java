package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * DTO utilizado para enviar información de errores al cliente.
 */
@Schema(
        name = "ErrorResponse",
        description = "Error Response"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    /**
     * Ruta del API que invocó el cliente.
     */
    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath;
    /**
     * Código de estado HTTP que representa el error.
     */
    @Schema(
            description = "Error message representing error happened in API"
    )
    private HttpStatus errorCode;

    /**
     * Mensaje que describe el error.
     */
    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;

    /**
     * Momento en que ocurrió el error.
     */
    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;
}
