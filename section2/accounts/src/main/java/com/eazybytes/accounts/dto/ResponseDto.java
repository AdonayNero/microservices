package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * DTO genérico para respuestas estándar del servicio.
 */
@Schema(
        name = "Response",
        description = "Shema to hold response details"

)
@Data
@AllArgsConstructor
public class ResponseDto {


    /** Código devuelto por la operación. */
    private String statusCode;

    /** Mensaje asociado al código de estado. */
    private String statusMsg;
}
